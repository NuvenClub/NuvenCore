package com.emanuelvini.nuven.core.bukkit.listener

import com.emanuelvini.nuven.core.bukkit.data.requester.impl.ProfileRequester
import com.emanuelvini.nuven.core.shared.configuration.chat.ChatConfiguration
import com.emanuelvini.nuven.core.shared.configuration.language.LanguageConfiguration
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.*

class ChatListener(
    private val profileRequester: ProfileRequester,
    private val chatConfiguration: ChatConfiguration,
    private val languageConfiguration: LanguageConfiguration
) : Listener {

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {


        val profile = profileRequester[event.player.name]

        if (profile == null) {
            event.isCancelled = true
            event.player.sendMessage("§cSeu perfil ainda está carregando...")
        } else {
            var message = event.message.replace("%", "%%").lowercase()
            var messageCapsFilter = event.message
            chatConfiguration.capslockSkipWords.forEach {
                messageCapsFilter = messageCapsFilter.replace(it, "")
            }
            val regex = Regex("""(?:https?://)?([a-zA-Z0-9.-]+)""")
            val joined = Regex("[^a-zA-Z0-9]").replace(event.message, "")

            val matches = regex.findAll(joined)
            for (blocked in chatConfiguration.blockedContent) {
                if (joined.contains(blocked) || message.contains(blocked)) {
                    event.player.sendMessage(
                        languageConfiguration.messageBlocked
                    )
                    event.isCancelled = true
                    return
                }
            }
/*
            if (matches.map { it.groupValues[1] }.toList().any {
                    !chatConfiguration.allowedDomains.contains(it)
                }) {
                event.player.sendMessage(
                    languageConfiguration.messageBlocked
                )
                return
            }
 */
            val capslockCount =messageCapsFilter.filter { it.isUpperCase() }.length
            if (capslockCount > 1 && (messageCapsFilter.length/capslockCount * 100 >= chatConfiguration.capslockPercentage)) message.lowercase()
            if (true && message.length >= 4) {
                chatConfiguration.messageReplace.forEach {
                    message = message
                        .replace(it.key.lowercase() + " ", it.value + " ")
                        .replace(" " + it.key.lowercase(), " " + it.value)
                }
                message =
                    message.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            }
            if (profile.role.specialChat) message = ChatColor.translateAlternateColorCodes('&', message)
            if (ChatColor.stripColor(message).isNullOrBlank()) return
            val outMessage =
                "${profile.role.prefix}${event.player.name}§7: ${if (profile.role.specialRole) "§f" else ""}${message}"
            if (!profile.role.staffRole) {
                event.isCancelled = true
                event.player.world.players.forEach { it.sendMessage(outMessage) }
            } else {
                event.format = "${profile.role.prefix}%s§7: ${if (profile.role.specialRole) "§f" else ""}${message}"
            }
        }

    }

}