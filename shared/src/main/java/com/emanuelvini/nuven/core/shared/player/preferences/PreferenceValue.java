package com.emanuelvini.nuven.core.shared.player.preferences;

import com.emanuelvini.nuven.core.shared.player.preferences.Preference;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PreferenceValue<T extends Enum<?>> {

    private Preference preference;

    private T value;

}
