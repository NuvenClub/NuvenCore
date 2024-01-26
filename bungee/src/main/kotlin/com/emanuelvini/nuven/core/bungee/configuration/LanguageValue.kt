package com.emanuelvini.nuven.core.bungee.configuration

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable
import java.util.function.Function
@TranslateColors
@ConfigFile("settings/language.yml")
class LanguageValue : ConfigurationInjectable {

    @ConfigField("commands.tag.applied")
    val tagApplied : String? = null
    @ConfigField("commands.tag.list")
    val tagList : String? = null

    @ConfigField("chat.message-blocked")
    val messageBlocked : String? = null
    companion object {
        val instance = LanguageValue()

        operator fun <T> get(
            function : Function<LanguageValue, T>
        ) : T {
            return function.apply(instance)!!
        }
    }

}