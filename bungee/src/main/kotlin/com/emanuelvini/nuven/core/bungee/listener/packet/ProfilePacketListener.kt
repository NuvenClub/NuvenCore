package com.emanuelvini.nuven.core.bungee.listener.packet

import com.emanuelvini.nuven.core.bungee.BungeeMain
import com.emanuelvini.nuven.core.bungee.data.repository.ProfileRepository
import com.emanuelvini.nuven.core.shared.player.profile.util.ProfileUtil
import com.google.common.io.ByteStreams
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.event.PluginMessageEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.api.scheduler.TaskScheduler
import net.md_5.bungee.event.EventHandler


class ProfilePacketListener(
    private val proxy: ProxyServer = ProxyServer.getInstance(),
    private val scheduler: TaskScheduler = proxy.scheduler,
    private val profileRepository: ProfileRepository,
    private val plugin: BungeeMain
) : Listener {


    @EventHandler
    fun onPluginMessaging(event: PluginMessageEvent) {
        if (!event.tag.equals("nvcore:main")) return
        val byteStream = ByteStreams.newDataInput(event.data)
        val subChannel = byteStream.readUTF()

        if (!subChannel.startsWith("profile")) return

        if (subChannel.endsWith("get")) {
            val name = byteStream.readUTF()
            val player = proxy.getPlayer(name)
            if (player != null) {
                val profile = profileRepository.getPlayerData(
                    ProxyServer.getInstance().getPlayer(name)
                )
                PacketUtil.sendGetProfile(profile, player)
            }
        } else {
            val data = byteStream.readUTF()
            val profile = ProfileUtil.from(data)
            profileRepository.saveData(profile)
        }
    }
}
