package com.emanuelvini.nuven.core.bungee.data.repository

import com.emanuelvini.nuven.core.bungee.data.PersistentRepository
import com.emanuelvini.nuven.core.bungee.data.adapter.ProfileAdapter
import com.emanuelvini.nuven.core.bungee.repository.PlayerPreferences
import com.emanuelvini.nuven.core.shared.player.preferences.Preference
import com.henryfabio.sqlprovider.executor.SQLExecutor
import java.util.function.Consumer

class PreferencesRepository(executor: SQLExecutor, private val preferences: List<Preference>)  :
    PersistentRepository<String, PlayerPreferences>(executor) {


    init {
        createTable()
    }

    override fun getData(name: String): PlayerPreferences {
        return cache.get(name).get()
    }

    override fun createTable() {
        val tableBuilder = StringBuilder()
        preferences.forEach(Consumer { preference: Preference ->
            tableBuilder
                .append(preference.id)
                .append(" ")
                .append("INT")
                .append(",")
        })
        executor
            .updateQuery(
                "CREATE TABLE IF NOT EXISTS player_preferences ( name VARCHAR(16), ${tableBuilder.substring(0, tableBuilder.length - 1)} )",
            )
    }

    override fun findData(name: String): PlayerPreferences {
        val value = executor.resultQuery(
            "SELECT * FROM nuvencore_preferences WHERE name = ?",
            { simpleStatement -> simpleStatement.set(1, name) },
            {
                val playerPreference = PlayerPreferences(name, hashMapOf())
                if (it.next()) {
                    preferences.forEach { pref ->
                        val v = it.get<Int>(pref.id)
                        playerPreference.values[pref] = v
                    }
                } else {
                    preferences.forEach { pref ->
                        playerPreference.values[pref] = pref.defaultValue
                    }
                }
                return@resultQuery playerPreference
            }
        )

        return value

    }

    override fun saveData(data: PlayerPreferences) {}
}
