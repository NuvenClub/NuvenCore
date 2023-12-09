package com.emanuelvini.nuven.core.shared.player.preferences.lobby.visibility

import com.emanuelvini.nuven.core.shared.player.preferences.Preference
import com.emanuelvini.nuven.core.shared.player.preferences.PreferenceValue
import com.emanuelvini.nuven.core.shared.player.preferences.value.OnOffPreferenceValue

class VisibilityValue(preference: Preference, value: OnOffPreferenceValue) :
    PreferenceValue<OnOffPreferenceValue?>(preference, value)
