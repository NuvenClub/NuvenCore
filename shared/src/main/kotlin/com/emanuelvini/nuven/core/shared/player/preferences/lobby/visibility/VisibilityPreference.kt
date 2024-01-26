package com.emanuelvini.nuven.core.shared.player.preferences.lobby.visibility

import com.emanuelvini.nuven.core.shared.player.preferences.Preference
import com.emanuelvini.nuven.core.shared.player.preferences.PreferenceValueMappedType
import com.emanuelvini.nuven.core.shared.player.preferences.value.OnOffPreferenceValue

class VisibilityPreference : Preference(
    "Jogadores Vísiveis",
    "player_visibility",
    OnOffPreferenceValue.ON.ordinal,
    PreferenceValueMappedType.ON_OFF)
