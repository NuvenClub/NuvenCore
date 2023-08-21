package com.emanuelvini.nuven.core.shared.player.profile.util;

import com.emanuelvini.nuven.core.shared.player.profile.Profile;
import com.emanuelvini.nuven.core.shared.player.ranking.Tag;
import lombok.val;

public class ProfileUtil {

    public static Profile from(String serialized) {
        val props = serialized.split(";");
        val owner = props[0];
        val cash = Double.parseDouble(props[1]);
        val tagProps = props[2].split(":");
        val tagOrder = Integer.parseInt(tagProps[0]);
        val tagName = tagProps[2];
        val tagTag = tagProps[3];
        val tagPermission = tagProps[4];

        return new Profile(
                owner,
                cash,
                new Tag(tagName, tagTag, tagOrder, tagPermission)
        );
    }

    public static String to(Profile profile) {

        val tag = profile.getTag();
        val cash = profile.getCash();
        val owner = profile.getOwner();

        return owner +
                ";" +
                cash +
                ";" +
                tag.getOrder() +
                ":" +
                tag.getName() +
                ":" +
                tag.getTag() +
                ":" +
                tag.getPermission() +
                ";";
    }

}
