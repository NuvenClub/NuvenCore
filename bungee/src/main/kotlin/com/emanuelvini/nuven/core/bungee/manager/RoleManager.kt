package com.emanuelvini.nuven.core.bungee.manager

import com.emanuelvini.nuven.core.shared.player.ranking.Role
import net.luckperms.api.model.PermissionHolder
import net.luckperms.api.model.data.DataType
import net.luckperms.api.node.NodeType
import net.md_5.bungee.api.connection.ProxiedPlayer
import java.util.stream.Collectors


class RoleManager {
    val roles: HashMap<Int, Role> = HashMap()


    fun available(player : ProxiedPlayer) : List<Role> {
        val roles = roles.values.filter {
            println(it.permission)
            player.hasPermission(it.permission)
        }.toMutableList()
        if (!roles.contains(this.roles[0])) roles.add(this.roles[0]!!)
        return roles
    }
    fun available(player : PermissionHolder) : List<Role> {
        val roles = roles.values.filter { role ->
            println(role.permission)
            player.getNodes().stream()
                .filter {
                    it.type == NodeType.INHERITANCE || it.type == NodeType.PERMISSION
                }
                .map {
                    it.key
                }
                .collect(Collectors.toList())
                .contains(role.permission)
        }.toMutableList()
        roles.add(this.roles[0]!!)
        return roles.toList()
    }
}
