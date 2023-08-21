package com.emanuelvini.nuven.core.shared.data.repository;

import com.emanuelvini.nuven.core.shared.player.preferences.lobby.visibility.VisibilityValue;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerPreferences {

    private String name;

    private VisibilityValue visibilityValue;

}
