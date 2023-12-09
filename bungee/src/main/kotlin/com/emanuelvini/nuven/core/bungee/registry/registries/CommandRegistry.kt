package com.emanuelvini.nuven.core.bungee.registry.registries

import com.emanuelvini.nuven.core.bungee.BungeeMain
import com.emanuelvini.nuven.core.bungee.command.TagCommand
import com.emanuelvini.nuven.core.bungee.registry.BungeeRegistry
import me.saiintbrisson.bungee.command.BungeeFrame

class CommandRegistry(plugin: BungeeMain) : BungeeRegistry(plugin) {
    override fun register() {
        val frame = BungeeFrame(plugin)
        frame.registerCommands(
            TagCommand(plugin.roleManager, plugin.profileRepository!!)
        )

    }
}