package com.emanuelvini.nuven.core.bungee.listener.packet

import com.emanuelvini.nuven.core.shared.player.profile.Profile
import com.emanuelvini.nuven.core.shared.player.profile.util.ProfileUtil
import com.google.common.io.ByteStreams
import net.md_5.bungee.api.connection.ProxiedPlayer

object PacketUtil {

    fun sendGetProfile(profile : Profile, player : ProxiedPlayer) {
        val data = ByteStreams.newDataOutput()
        data.writeUTF("profile-get")
        data.writeUTF(ProfileUtil.to(profile))
        player.server.sendData("nvcore:main", data.toByteArray())
    }

}