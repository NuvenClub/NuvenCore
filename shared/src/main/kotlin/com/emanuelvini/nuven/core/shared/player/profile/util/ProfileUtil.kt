package com.emanuelvini.nuven.core.shared.player.profile.util

import com.emanuelvini.nuven.core.shared.player.profile.Profile
import com.emanuelvini.nuven.core.shared.player.ranking.Role


object ProfileUtil {
    fun from(serialized: String): Profile {
        val props = serialized.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val owner = props[0]
        val cash = props[1].toDouble()
        val roleProps = props[2].split(":")
        val roleOrder = roleProps[0].toInt()
        val rolePrefix = roleProps[1]
        val rolePermission = roleProps[2]
        val roleStaff = roleProps[3].toBoolean()
        val roleSpecialRole = roleProps[4].toBoolean()
        val roleJoinBroadcast = roleProps[5].toBoolean()
        val roleSpecialChat = roleProps[6].toBoolean()
        val roleName = roleProps[7]
        return Profile(
            owner,
            cash,
            Role(roleName, rolePrefix, rolePermission, roleOrder, roleStaff, roleSpecialRole, roleJoinBroadcast, roleSpecialChat)
        )
    }

    fun to(profile: Profile): String {
        val role = profile.role
        val cash = profile.cash
        val owner = profile.owner
        return "${owner};$cash;${role.order}:${role.prefix}:${role.permission}:${role.staffRole}:${role.specialRole}:${role.joinBroadcast}:${role.specialChat}:${role.name}"
    }
}
