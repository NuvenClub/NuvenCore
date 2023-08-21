package com.emanuelvini.nuven.core.bungee.data.adapter;

import com.emanuelvini.nuven.core.bungee.BungeeMain;
import com.emanuelvini.nuven.core.bungee.manager.TagManager;
import com.emanuelvini.nuven.core.shared.player.profile.Profile;
import com.henryfabio.sqlprovider.executor.adapter.SQLResultAdapter;
import com.henryfabio.sqlprovider.executor.result.SimpleResultSet;

public class ProfileAdapter implements SQLResultAdapter<Profile> {

    private final TagManager tagManager = BungeeMain.instance().getTagManager();

    @Override
    public Profile adaptResult(SimpleResultSet simpleResultSet) {
        return new Profile(
                simpleResultSet.get("owner"),
                simpleResultSet.get("cash"),
                tagManager.getTags().get((int) simpleResultSet.get("tag"))
        );
    }
}
