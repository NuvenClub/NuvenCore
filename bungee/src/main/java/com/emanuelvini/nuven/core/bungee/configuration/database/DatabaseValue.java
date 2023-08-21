package com.emanuelvini.nuven.core.bungee.configuration.database;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.function.Function;

@Accessors(fluent = true)
@Getter
@ConfigFile("database.yml")
public class DatabaseValue implements ConfigurationInjectable {

    @Getter
    private static DatabaseValue instance = new DatabaseValue();

    @ConfigField("address")
    private String address;

    @ConfigField("username")
    private String username;

    @ConfigField("password")
    private String password;

    @ConfigField("database")
    private String database;

    public static <T> T get(Function<DatabaseValue, T> function) {
        return function.apply(instance);
    }

}
