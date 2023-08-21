package com.emanuelvini.nuven.core.shared.player.preferences.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OnOffPreferenceValue {

    OFF("Desligado"),
    ON("Ligado");

    private final String value;

}
