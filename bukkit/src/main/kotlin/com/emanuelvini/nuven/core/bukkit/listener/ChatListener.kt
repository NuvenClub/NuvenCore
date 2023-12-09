package com.emanuelvini.nuven.core.bukkit.listener

import com.emanuelvini.nuven.core.bukkit.data.requester.impl.ProfileRequester
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatListener (
    private val profileRequester: ProfileRequester
) : Listener {

    @EventHandler
    fun onChat(event : AsyncPlayerChatEvent) {
        val profile = profileRequester[event.player.name]
        //xxl
        if (profile == null) {
            event.isCancelled = true
            event.player.sendMessage("§cSeu perfil ainda está carregando...")
        } else {
            var message = event.message
            if (profile.role.coloredChat) message = ChatColor.translateAlternateColorCodes('&', message)
            if (ChatColor.stripColor(message).isNullOrBlank()) event.isCancelled = true
            event.format = "${profile.role.prefix}%s§7: ${if (profile.role.specialRole) "§f" else ""}${message.replace("%", "%%")}"
        }
    }

}