package com.emanuelvini.nuven.core.bungee.registry.primary

import com.emanuelvini.nuven.core.bungee.BungeeMain
import com.emanuelvini.nuven.core.bungee.configuration.ChatValue
import com.emanuelvini.nuven.core.bungee.configuration.LanguageValue
import com.emanuelvini.nuven.core.shared.database.DatabaseValue
import com.henryfabio.minecraft.configinjector.bungee.injector.BungeeConfigurationInjector


class ConfigurationRegistry (
    private val plugin: BungeeMain
) {

    fun register() {
        val bungeeInjector = BungeeConfigurationInjector(plugin)
        plugin.settingsFolder.mkdirs()
        bungeeInjector.saveDefaultConfiguration(
            plugin,
            "database.yml",
            "settings/language.yml",
            "settings/chat.yml"
        )
        bungeeInjector.injectConfiguration(
            DatabaseValue.instance,
            LanguageValue.instance,
            ChatValue.instance
        )
    }
}
