package com.emanuelvini.nuven.core.bungee.data.repository;

import com.emanuelvini.nuven.core.bungee.data.adapter.ProfileAdapter;
import com.emanuelvini.nuven.core.bungee.manager.TagManager;
import com.emanuelvini.nuven.core.shared.player.profile.Profile;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;

import java.time.Duration;

@AllArgsConstructor
public class ProfileRepository {

    private SQLExecutor executor;

    private TagManager tagManager;
    private final AsyncLoadingCache<String, Profile> cache = Caffeine
            .newBuilder()
            .expireAfterAccess(Duration.ofMinutes(5))
            .removalListener((RemovalListener<String, Profile>) (name, profile, cause) -> {
                if (name != null && profile != null) {
                    saveProfile(profile);
                }
            })
            .evictionListener((name, profile, cause) -> {
                if (name != null && profile != null) {
                    saveProfile(profile);
                }
            })
            .maximumSize(1000)
            .buildAsync(this::selectProfile);

    @SneakyThrows
    public Profile getProfile(String name) {
        return cache.get(name).get();
    }

    private Profile createProfile(String name) {
        executor.updateQuery("INSERT INTO nuvencore_profiles VALUES (?,?,?)", simpleStatement -> {
            simpleStatement.set(1, name);
            simpleStatement.set(2, 0);
            simpleStatement.set(3, 0);
        });
        return new Profile(name, 0, tagManager.getTags().get(0));
    }
    private Profile selectProfile(String name) {
        val profile = executor.resultOneQuery("SELECT * FROM nuvencore_profiles WHERE name = ?", simpleStatement -> {
            simpleStatement.set(1, name);
        }, ProfileAdapter.class);
        return profile == null ? createProfile(name) : profile;
    }

    private void saveProfile(Profile profile) {
        executor.updateQuery("UPDATE nuvencore_profiles SET cash = ?, tag = ? WHERE name = ?", simpleStatement -> {
            simpleStatement.set(3, profile.getOwner());
            simpleStatement.set(2, profile.getCash());
            simpleStatement.set(3, profile.getTag().getOrder());
        });
    }
}
