package com.emanuelvini.nuven.core.shared.player.preferences.lobby.visibility;


import com.emanuelvini.nuven.core.shared.player.preferences.Preference;
import com.emanuelvini.nuven.core.shared.player.preferences.PreferenceValue;
import com.emanuelvini.nuven.core.shared.player.preferences.value.OnOffPreferenceValue;

public class VisibilityValue extends PreferenceValue<OnOffPreferenceValue> {
    public VisibilityValue(Preference preference, OnOffPreferenceValue value) {
        super(preference, value);
    }
}
