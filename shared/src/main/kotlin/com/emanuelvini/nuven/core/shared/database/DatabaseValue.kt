package com.emanuelvini.nuven.core.shared.database

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable
import java.util.function.Function


@ConfigFile("database.yml")
class DatabaseValue : ConfigurationInjectable {
    @ConfigField("address")
    val address: String? = null

    @ConfigField("username")
    val username: String? = null

    @ConfigField("password")
    val password: String? = null

    @ConfigField("database")
    val database: String? = null

    companion object {
        val instance = DatabaseValue()
        operator fun <T> get(function: Function<DatabaseValue, T?>): T? {
            return function.apply(instance)
        }
    }
}
