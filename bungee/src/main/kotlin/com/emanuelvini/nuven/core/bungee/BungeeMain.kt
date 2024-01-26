package com.emanuelvini.nuven.core.bungee

import com.emanuelvini.nuven.core.bungee.data.repository.PreferencesRepository
import com.emanuelvini.nuven.core.bungee.data.repository.ProfileRepository
import com.emanuelvini.nuven.core.bungee.logger.BungeeLogger
import com.emanuelvini.nuven.core.bungee.manager.RoleManager
import com.emanuelvini.nuven.core.bungee.registry.primary.ConfigurationRegistry
import com.emanuelvini.nuven.core.bungee.registry.registries.CommandRegistry
import com.emanuelvini.nuven.core.bungee.registry.registries.PacketListenerRegistry
import com.emanuelvini.nuven.core.bungee.registry.registries.RoleRegistry
import com.emanuelvini.nuven.core.shared.database.DatabaseValue
import com.emanuelvini.nuven.core.shared.player.preferences.lobby.visibility.VisibilityPreference
import com.emanuelvini.nuven.core.shared.registry.Registry
import com.google.common.io.ByteStreams
import com.henryfabio.sqlprovider.connector.type.impl.MySQLDatabaseType
import com.henryfabio.sqlprovider.executor.SQLExecutor
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.plugin.Plugin
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.function.Consumer


class BungeeMain : Plugin() {
    val bungeeLogger: BungeeLogger = BungeeLogger(ProxyServer.getInstance().console)
    val settingsFolder: File = File(dataFolder, "settings")
    val roleManager: RoleManager = RoleManager()
    var profileRepository: ProfileRepository? = null
    var preferencesRepository: PreferencesRepository? = null
    var registries: List<Registry>? = null
    val preferences = listOf(
        VisibilityPreference()
    )
    override fun onLoad() {
        instance = this

    }

    override fun onEnable() {
        super.onEnable()

        val configurationRegistry = ConfigurationRegistry(this)
        configurationRegistry.register()
        try {
            val executor = SQLExecutor(
                MySQLDatabaseType
                    .builder()
                    .database(DatabaseValue[DatabaseValue::database])
                    .username(DatabaseValue[DatabaseValue::username])
                    .password(DatabaseValue[DatabaseValue::password])
                    .address(DatabaseValue[DatabaseValue::address])
                    .build()
                    .connect()
            )

            profileRepository =
                ProfileRepository(executor, roleManager)
            preferencesRepository = PreferencesRepository(executor, preferences)

            preferencesRepository!!.createTable()
            profileRepository!!.createTable()
            registries = listOf(
                PacketListenerRegistry(this),
                RoleRegistry(this, profileRepository!!),
                CommandRegistry(this)
            )
            registries!!.forEach(Consumer { obj: Registry -> obj.register() })
            bungeeLogger.success("Plugin habilitado com sucesso.")
        } catch (e: Exception) {
            bungeeLogger.error("Falha ao acessar o banco de dados, por favor informe abaixo:")
            e.printStackTrace()
        }
    }

    override fun onDisable() {
        super.onDisable()
        registries!!.forEach(Consumer { obj: Registry -> obj.unregister() })
        bungeeLogger.success("Plugin desabilitado com sucesso.")
    }

    companion object {
        var instance: BungeeMain? = null
    }
}
