package com.emanuelvini.nuven.core.api.minigame;

import com.emanuelvini.nuven.core.api.minigame.profile.MiniGameProfile;

public interface MiniGame {

    String getName();

    MiniGameProfile getProfile(String name);

}
