package com.emanuelvini.nuven.core.bukkit.data.requester.impl

import com.emanuelvini.nuven.core.bukkit.BukkitMain
import com.emanuelvini.nuven.core.bukkit.data.requester.Requester
import com.emanuelvini.nuven.core.shared.player.profile.Profile
import com.emanuelvini.nuven.core.shared.player.profile.util.ProfileUtil
import com.google.common.io.ByteStreams
import org.bukkit.entity.Player
import org.bukkit.plugin.messaging.PluginMessageListener
import java.util.concurrent.CompletableFuture


class ProfileRequester (private val plugin: BukkitMain) : Requester<String, Profile?>() {



    override fun on(message: ByteArray) {
        val data = ByteStreams.newDataInput(message)
        val sbc = data.readUTF()

        if (sbc == "profile-get") {
            val c = data.readUTF()
            val profile = ProfileUtil.from(c)
            cache.asMap()[profile.owner] = profile
            println(waiters[profile.owner])
            waiters[profile.owner]?.forEach { it(profile) }

        }
    }

    override fun save(key: String, value: Profile?) {
        if (value != null) {
            val m = ByteStreams.newDataOutput()
            m.writeUTF("profile-save")
            m.writeUTF(ProfileUtil.to(value))
            plugin.server.onlinePlayers.first().sendPluginMessage(plugin, "nvcore:main", m.toByteArray())
        }
    }




    override operator fun get(key: String): Profile? {
        val value = cache.getIfPresent(key)
        if (value == null) {
            requestData(key)

        }


        return value
    }
    fun requestData(key : String) {
        val m = ByteStreams.newDataOutput()
        m.writeUTF("profileget")
        m.writeUTF(key  )
        plugin.server.onlinePlayers.first().sendPluginMessage(plugin, "nvcore:main", m.toByteArray())
    }

}
