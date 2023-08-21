package com.emanuelvini.nuven.core.shared.player.ranking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Tag {

    private String name;

    private String tag;

    private int order;

    private String permission;

}
