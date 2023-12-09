package com.emanuelvini.nuven.core.bukkit.registry.primary

import com.emanuelvini.nuven.core.bukkit.BukkitMain
import com.emanuelvini.nuven.core.shared.database.DatabaseValue
import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector

class ConfigurationRegistry (
    private val plugin: BukkitMain
) {
    fun register() {
        val bungeeInjector = BukkitConfigurationInjector(plugin)
        bungeeInjector.saveDefaultConfiguration(
            plugin,
            "database.yml"
        )
        bungeeInjector.injectConfiguration(
            DatabaseValue.instance
        )
    }
}