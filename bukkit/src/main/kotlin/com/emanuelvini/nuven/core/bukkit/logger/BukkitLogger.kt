package com.emanuelvini.nuven.core.bukkit.logger

import com.emanuelvini.nuven.core.shared.logger.PlatformLogger
import org.bukkit.command.ConsoleCommandSender

class BukkitLogger (
    private val sender : ConsoleCommandSender
) : PlatformLogger {
    override fun log(vararg messages: String?) {
        sender.sendMessage("§b[NuvenCore] §e[BUKKIT]: §f${messages.joinToString(separator = " ")}")
    }
}