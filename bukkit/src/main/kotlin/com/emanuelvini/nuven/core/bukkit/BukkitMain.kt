package com.emanuelvini.nuven.core.bukkit

import com.emanuelvini.nuven.core.bukkit.configuration.AckData
import com.emanuelvini.nuven.core.bukkit.data.requester.impl.ProfileRequester
import com.emanuelvini.nuven.core.bukkit.listener.ChatListener
import com.emanuelvini.nuven.core.bukkit.logger.BukkitLogger
import com.emanuelvini.nuven.core.bukkit.registry.BukkitRegistry
import com.emanuelvini.nuven.core.bukkit.registry.primary.ConfigurationRegistry
import com.emanuelvini.nuven.core.bukkit.registry.registries.TabRegistry
import com.emanuelvini.nuven.core.shared.configuration.util.ChatConfigurationUtil
import com.emanuelvini.nuven.core.shared.configuration.util.LanguageConfigurationUtil
import com.emanuelvini.nuven.core.shared.database.DatabaseValue
import com.emanuelvini.nuven.core.shared.player.preferences.util.PreferenceUtil
import com.google.common.io.ByteStreams
import com.henryfabio.sqlprovider.connector.type.impl.MySQLDatabaseType
import com.henryfabio.sqlprovider.executor.SQLExecutor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.PluginMessageListener
import kotlin.reflect.KClass

class BukkitMain : JavaPlugin(), Listener, PluginMessageListener {

    val ackData = AckData()

    val bukkitLogger: BukkitLogger =
        BukkitLogger(server.consoleSender)

    val profileRequester = ProfileRequester(this)


    private var registries : HashMap<KClass<out BukkitRegistry>, BukkitRegistry> = hashMapOf()

    private val requesters = listOf(profileRequester)

    private val listeners = listOf<Listener>(

    )

    private var ack = false

    private var executor: SQLExecutor? = null

    fun disableTab() {

    }

    private fun enableTab() {
        
    }
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

            server.messenger.registerOutgoingPluginChannel(this, "nvcore:main")
            server.messenger.registerIncomingPluginChannel(this, "nvcore:main", this)
            server.pluginManager.registerEvents(this, this)
            listeners.forEach {
                server.pluginManager.registerEvents(it, this)
            }
            bukkitLogger.success("Plugin habilitado com sucesso. Aguardando ACK do BungeeCord.")
        } catch (e: Exception) {
            bukkitLogger.error("Falha ao acessar o banco de dados, por favor informe abaixo:")
            e.printStackTrace()
        }
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        server.scheduler.runTaskLater(this,  {
            val d = ByteStreams.newDataOutput()
            d.writeUTF("ack")
            server.onlinePlayers.first()!!.sendPluginMessage(this, "nvcore:main", d.toByteArray())
        }, 20)
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
            if (!ack) {
                val data = ByteStreams.newDataInput(message)
                val sbc = data.readUTF()
                if (sbc == "ack") {
                    val adc = data.readUTF().split("///")

                    ackData.chatConfiguration = ChatConfigurationUtil.from(adc[0])
                    ackData.languageConfiguration = LanguageConfigurationUtil.from(adc[1])
                    ackData.preferences.addAll(
                        PreferenceUtil.from(adc[2])
                    )
                    requesters.forEach { it.on(message) }
                    ack = true
                    server.pluginManager.registerEvents(ChatListener(profileRequester, ackData.chatConfiguration!!, ackData.languageConfiguration!!), this)
                    server.consoleSender.sendMessage(("§aACK confirmado do BungeeCord!!!"))
                    ackData.preferences.forEach {
                        println(it.name)
                    }
                    registries = hashMapOf(
                        TabRegistry::class to TabRegistry(this)
                    )
                    registries.values.forEach { it.register() }
                }

            } else {
                requesters.forEach {it.on(message)}
            }
        }
    }
}