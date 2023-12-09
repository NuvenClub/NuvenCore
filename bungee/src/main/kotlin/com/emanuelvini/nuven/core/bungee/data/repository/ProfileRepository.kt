package com.emanuelvini.nuven.core.bungee.data.repository

import com.emanuelvini.nuven.core.bungee.data.PersistentRepository
import com.emanuelvini.nuven.core.bungee.data.adapter.ProfileAdapter
import com.emanuelvini.nuven.core.bungee.manager.RoleManager
import com.emanuelvini.nuven.core.shared.player.profile.Profile
import com.henryfabio.sqlprovider.executor.SQLExecutor
import net.md_5.bungee.api.connection.ProxiedPlayer


class ProfileRepository(
    executor: SQLExecutor,
    private val roleManager: RoleManager
) : PersistentRepository<String, Profile>(executor) {



    override fun getData(name: String): Profile {
        return cache.get(name).get()
    }

    private fun createProfile(name: String): Profile {
        executor.updateQuery("INSERT INTO nuvencore_profiles VALUES (?,?,?)") { simpleStatement ->
            simpleStatement.set(1, name)
            simpleStatement.set(2, 0)
            simpleStatement.set(3, 0)
        }
        return Profile(name, 0.0, roleManager.roles[0]!!)
    }

    fun getPlayerData(player : ProxiedPlayer) : Profile {
        val data = getData(player.name)
        if (!player.hasPermission(data.role.permission)) {
            data.role = roleManager.roles[0]!!
        }
        return data
    }

    override fun findData(name: String): Profile {
        val value = executor.resultOneQuery(
            "SELECT * FROM nuvencore_profiles WHERE name = ?",
            { simpleStatement -> simpleStatement.set(1, name) },
            ProfileAdapter::class.java
        )
        print (value != null)
        return value ?: createProfile(name)
    }

    override fun saveData(data: Profile) {
        executor.updateQuery("UPDATE nuvencore_profiles SET cash = ?, tag = ? WHERE name = ?") { simpleStatement ->
            simpleStatement.set(1, data.cash)
            simpleStatement.set(2, data.role.order)
            simpleStatement.set(3, data.owner)
        }
    }

    override fun createTable() {
        executor.updateQuery("CREATE TABLE IF NOT EXISTS nuvencore_profiles (name VARCHAR(16), cash DOUBLE, tag INT)")
    }
}
