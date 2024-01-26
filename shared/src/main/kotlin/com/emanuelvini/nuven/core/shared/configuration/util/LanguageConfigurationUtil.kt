package com.emanuelvini.nuven.core.shared.configuration.util

import com.emanuelvini.nuven.core.shared.configuration.language.LanguageConfiguration

object LanguageConfigurationUtil {

    fun from(string: String) : LanguageConfiguration {
        val props = string.split("^/")
        return LanguageConfiguration(
            props[0]
        )
    }

    fun to(languageConfiguration: LanguageConfiguration) : String {
        return "${languageConfiguration.messageBlocked}"
    }

}