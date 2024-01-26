package com.emanuelvini.nuven.core.bukkit.configuration

import com.emanuelvini.nuven.core.shared.configuration.chat.ChatConfiguration
import com.emanuelvini.nuven.core.shared.configuration.language.LanguageConfiguration
import com.emanuelvini.nuven.core.shared.player.preferences.Preference

class AckData {

    var chatConfiguration : ChatConfiguration? = null

    var languageConfiguration : LanguageConfiguration? = null

    val preferences = mutableListOf<Preference>()

}