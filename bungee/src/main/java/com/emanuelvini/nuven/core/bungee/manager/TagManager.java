package com.emanuelvini.nuven.core.bungee.manager;

import com.emanuelvini.nuven.core.shared.player.ranking.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

@AllArgsConstructor
@Getter
public class TagManager {

    private final HashMap<Integer, Tag> tags = new HashMap<>();



}
