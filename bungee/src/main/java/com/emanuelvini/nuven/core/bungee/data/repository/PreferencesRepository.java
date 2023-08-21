package com.emanuelvini.nuven.core.bungee.data.repository;



import com.emanuelvini.nuven.core.bungee.data.PersistentRepository;
import com.emanuelvini.nuven.core.shared.data.repository.PlayerPreferences;
import com.emanuelvini.nuven.core.shared.player.preferences.Preference;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import lombok.val;

import java.util.List;



public class PreferencesRepository extends PersistentRepository<String, PlayerPreferences> {

    private final List<Preference> preferences;

    public PreferencesRepository(SQLExecutor executor, List<Preference> preferences) {
        super(executor);
        this.preferences = preferences;
        createTable();
    }

    @Override
    public void createTable() {
        val tableBuilder = new StringBuilder();

        preferences.forEach(preference -> {
            tableBuilder
                    .append(preference.getId())
                    .append(" ")
                    .append("INT")
                    .append(",")
            ;
        });

        
        executor
                .updateQuery(String
                        .format("CREATE TABLE IF NOT EXISTS player_preferences ( %s )",
                                tableBuilder.substring(0, tableBuilder.length()-1)
                        )
                );
    }

    @Override
    public PlayerPreferences findData(String name) {
        return null;
    }

    @Override
    public void saveData(PlayerPreferences data) {

    }
}
