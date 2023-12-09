package com.emanuelvini.nuven.core.bungee.logger

import com.emanuelvini.nuven.core.shared.logger.PlatformLogger
import net.md_5.bungee.api.CommandSender

import net.md_5.bungee.api.chat.TextComponent


class BungeeLogger (
    private val console: CommandSender
): PlatformLogger {

    override fun log(vararg messages: String?) {
        console.sendMessage(
            "§b[NuvenCore] §e[BUNGEE]: §f${messages.joinToString(separator = " ")}"
        )
    }
}
