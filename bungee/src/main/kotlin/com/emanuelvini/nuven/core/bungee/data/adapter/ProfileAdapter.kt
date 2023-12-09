package com.emanuelvini.nuven.core.bungee.data.adapter

import com.emanuelvini.nuven.core.bungee.BungeeMain
import com.emanuelvini.nuven.core.bungee.manager.RoleManager
import com.emanuelvini.nuven.core.shared.player.profile.Profile
import com.henryfabio.sqlprovider.executor.adapter.SQLResultAdapter
import com.henryfabio.sqlprovider.executor.result.SimpleResultSet

class ProfileAdapter : SQLResultAdapter<Profile> {
    private val roleManager: RoleManager = BungeeMain.instance!!.roleManager
    override fun adaptResult(simpleResultSet: SimpleResultSet): Profile {
        val order = simpleResultSet.get("tag") as Int
        print(order)
        print(roleManager.roles.size)
        return Profile(
            simpleResultSet.get("name"),
            simpleResultSet.get("cash"),
            (if (roleManager.roles.size <= order) roleManager.roles[0] else roleManager.roles[order])!!
        )
    }
}
