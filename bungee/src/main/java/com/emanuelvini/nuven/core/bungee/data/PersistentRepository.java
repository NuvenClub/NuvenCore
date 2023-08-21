package com.emanuelvini.nuven.core.bungee.data;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import lombok.AllArgsConstructor;

@AllArgsConstructor

public abstract class PersistentRepository<Q, T> {

    protected SQLExecutor executor;

    protected final AsyncLoadingCache<Q, T> cache = Caffeine
            .newBuilder()
            .evictionListener((RemovalListener<Q, T>) (key, value, cause) -> {
                if (value != null && key != null) {
                    saveData(value);
                }
            })
            .removalListener((key, value, cause) -> {
                if (value != null && key != null) {
                    saveData(value);
                }
            })
            .buildAsync(this::findData);

    public abstract void createTable();

    public abstract T findData(Q name);

    public abstract void saveData(T data);



}
