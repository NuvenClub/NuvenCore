package com.emanuelvini.nuven.core.bungee.registry;

import com.emanuelvini.nuven.core.bungee.BungeeMain;
import com.emanuelvini.nuven.core.bungee.configuration.database.DatabaseValue;
import com.henryfabio.minecraft.configinjector.bungee.injector.BungeeConfigurationInjector;
import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
public class ConfigurationRegistry {

    private BungeeMain plugin;

    public void register() {
        val bungeeInjector = new BungeeConfigurationInjector(plugin);

        bungeeInjector.saveDefaultConfiguration(plugin,
                "database.yml"
                );

        bungeeInjector.injectConfiguration(
                DatabaseValue.instance()
        );
    }

}
