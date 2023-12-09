package com.emanuelvini.nuven.core.bungee.data.repository

import com.emanuelvini.nuven.core.bungee.data.PersistentRepository
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
        TODO("Not yet implemented")
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
                "CREATE TABLE IF NOT EXISTS player_preferences ( ${tableBuilder.substring(0, tableBuilder.length - 1)} )",
            )
    }

    override fun findData(name: String): PlayerPreferences? {
        return null
    }

    override fun saveData(data: PlayerPreferences) {}
}
