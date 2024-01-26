package com.emanuelvini.nuven.core.bungee.repository

import com.emanuelvini.nuven.core.shared.player.preferences.Preference
import com.emanuelvini.nuven.core.shared.player.preferences.lobby.visibility.VisibilityValue

class PlayerPreferences(
    val name: String,
    val values : HashMap<Preference, Int>
)