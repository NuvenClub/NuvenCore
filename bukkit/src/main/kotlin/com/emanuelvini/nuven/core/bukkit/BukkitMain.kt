package com.emanuelvini.nuven.core.bukkit

import com.emanuelvini.nuven.core.bukkit.data.requester.impl.ProfileRequester
import com.emanuelvini.nuven.core.bukkit.listener.ChatListener
import com.emanuelvini.nuven.core.bukkit.logger.BukkitLogger
import com.emanuelvini.nuven.core.bukkit.registry.primary.ConfigurationRegistry
import com.emanuelvini.nuven.core.shared.database.DatabaseValue
import com.google.common.io.ByteStreams
import com.henryfabio.sqlprovider.connector.type.impl.MySQLDatabaseType
import com.henryfabio.sqlprovider.executor.SQLExecutor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.PluginMessageListener

class BukkitMain : JavaPlugin(), Listener, PluginMessageListener {

    val bukkitLogger: BukkitLogger =
        BukkitLogger(server.consoleSender)

    private val profileRequester = ProfileRequester(this)

    private val requesters = listOf(profileRequester)

    private val listeners = listOf(
        ChatListener(profileRequester),
    )

    private var ack = false

    private var executor: SQLExecutor? = null

    override fun onEnable() {
        bukkitLogger.log("Inicializando plugin...")
        val configurationRegistry = ConfigurationRegistry(this)
        server.messenger.registerOutgoingPluginChannel(this, "BungeeCord");
        configurationRegistry.register()
        try {
            executor = SQLExecutor(
                MySQLDatabaseType
                    .builder()
                    .database(DatabaseValue[DatabaseValue::database])
                    .username(DatabaseValue[DatabaseValue::username])
                    .password(DatabaseValue[DatabaseValue::password])
                    .address(DatabaseValue[DatabaseValue::address])
                    .build()
                    .connect()
            )
            listeners.forEach {
                server.pluginManager.registerEvents(it, this)
            }

            server.pluginManager.registerEvents(this, this)
            server.messenger.registerOutgoingPluginChannel(this, "nvcore:main")
            server.messenger.registerIncomingPluginChannel(this, "nvcore:main", this)
            bukkitLogger.success("Plugin habilitado com sucesso. Aguardando ACK do BungeeCord.")
        } catch (e: Exception) {
            bukkitLogger.error("Falha ao acessar o banco de dados, por favor informe abaixo:")
            e.printStackTrace()
        }
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        server.scheduler.runTaskLater(this, {
            if (!ack) event.player.kickPlayer("§cACK do BungeeCord não recebido!")
            else {
                PlayerJoinEvent.getHandlerList().unregister(this as Listener)
                event.player.sendMessage("§aACK confirmado do BungeeCord!!!")
            }
        }, 20L * 5)
    }

    override fun onPluginMessageReceived(channel: String?, player: Player?, message: ByteArray) {
        if (channel == "nvcore:main") {

            if (ack) {
                val data = ByteStreams.newDataInput(message)
                val sbc = data.readUTF()
                if (sbc != "ack") {
                    requesters.forEach { it.on(message) }
                }
            }
            else {
                val data = ByteStreams.newDataInput(message)
                val sbc = data.readUTF()
                if (sbc == "ack") ack = true
                server.consoleSender.sendMessage(("§aACK confirmado do BungeeCord!!!"))
            }
        }
    }
}