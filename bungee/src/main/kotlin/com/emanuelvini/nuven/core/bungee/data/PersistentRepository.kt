package com.emanuelvini.nuven.core.bungee.data

import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.RemovalListener
import com.henryfabio.sqlprovider.executor.SQLExecutor


abstract class PersistentRepository<Q, T> (
   val executor: SQLExecutor
) {

    protected val cache: AsyncLoadingCache<Q, T> = Caffeine
        .newBuilder()
        .evictionListener(RemovalListener<Q, T> { key, value, _ ->
            if (value != null && key != null) {
                saveData(value)
            }
        })
        .removalListener { key : Q?, value : T?, _ ->
            if (value != null && key != null) {
                saveData(value)
            }
        }
        .buildAsync { name: Q -> findData(name) }

    abstract fun getData(name : Q) : T
    abstract fun createTable()
    abstract fun findData(name: Q): T?
    abstract fun saveData(data: T)
}
