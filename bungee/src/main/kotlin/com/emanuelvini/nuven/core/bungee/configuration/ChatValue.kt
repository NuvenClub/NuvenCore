package com.emanuelvini.nuven.core.bungee.configuration

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable
import java.util.function.Function

@ConfigFile("settings/chat.yml")
class ChatValue : ConfigurationInjectable {

    @ConfigField("delay")
    val delay : Double? = null

    @ConfigField("replaces")
    val messageReplaces : List<String>? = null

    @ConfigField("min-length")
    val minLength : Int? = null

    @ConfigField("capslock.percentage")
    val capslockPercentage : Int? = null

    @ConfigField("capslock.skip-words")
    val capslockSkipWords : List<String>? = null

    @ConfigField("censor.punishment-command")
    val punishCommand : String? = null

    @ConfigField("censor.allowed-domains")
    val allowedDomains : List<String>? = null

    @ConfigField("censor.blocked-content")
    val blockedContent : List<String>? = null
    companion object {
        val instance = ChatValue()

        operator fun <T> get(
            function : Function<ChatValue, T>
        ) : T {
            return function.apply(instance)
        }

    }

}