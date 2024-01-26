package com.emanuelvini.nuven.core.bungee.listener.packet

import com.emanuelvini.nuven.core.bungee.BungeeMain
import com.emanuelvini.nuven.core.bungee.configuration.ChatValue
import com.emanuelvini.nuven.core.bungee.configuration.LanguageValue
import com.emanuelvini.nuven.core.bungee.data.repository.ProfileRepository
import com.emanuelvini.nuven.core.shared.configuration.chat.ChatConfiguration
import com.emanuelvini.nuven.core.shared.configuration.language.LanguageConfiguration
import com.emanuelvini.nuven.core.shared.configuration.util.ChatConfigurationUtil
import com.emanuelvini.nuven.core.shared.configuration.util.LanguageConfigurationUtil
import com.emanuelvini.nuven.core.shared.player.preferences.Preference
import com.emanuelvini.nuven.core.shared.player.preferences.util.PreferenceUtil
import com.emanuelvini.nuven.core.shared.player.profile.util.ProfileUtil
import com.google.common.io.ByteStreams
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.event.PluginMessageEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.api.scheduler.TaskScheduler
import net.md_5.bungee.event.EventHandler

class AckPacketListener(
    private val proxy: ProxyServer = ProxyServer.getInstance(),
    private val preferences: List<Preference>
) : Listener {

    private val chatConfiguration = ChatConfiguration(
        ChatValue[ChatValue::delay]!!,
        ChatConfigurationUtil.toHashMapChatConfiguration(ChatValue[ChatValue::messageReplaces]!!.toList()),
        ChatValue[ChatValue::minLength]!!,
        ChatValue[ChatValue::capslockPercentage]!!,
        ChatValue[ChatValue::capslockSkipWords]!!,
        ChatValue[ChatValue::allowedDomains]!!,
        ChatValue[ChatValue::punishCommand]!!,
        ChatValue[ChatValue::blockedContent]!!
    )

    private val languageConfiguration = LanguageConfiguration(
        LanguageValue[LanguageValue::messageBlocked]!!
    )




    @EventHandler
    fun onPluginMessaging(event: PluginMessageEvent) {
        if (!event.tag.equals("nvcore:main")) return
        val byteStream = ByteStreams.newDataInput(event.data)
        val subChannel = byteStream.readUTF()
        if (subChannel == "ack") {
            val out = ByteStreams.newDataOutput()
            val ackDataString =
                listOf(
                    ChatConfigurationUtil.to(
                        chatConfiguration
                    ),
                    LanguageConfigurationUtil.to(languageConfiguration),
                    PreferenceUtil.to(preferences)
                ).joinToString("///")
            println("Data: $ackDataString")
            out.writeUTF("ack")
            out.writeUTF(ackDataString)
            val b = out.toByteArray()
            proxy.servers.forEach {
                it.value.sendData("nvcore:main", b)
            }
        }
    }
}