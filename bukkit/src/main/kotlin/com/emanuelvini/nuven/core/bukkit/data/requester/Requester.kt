package com.emanuelvini.nuven.core.bukkit.data.requester

import com.emanuelvini.nuven.core.bukkit.BukkitMain
import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.RemovalListener
import java.time.Duration


abstract class Requester<K, V> {
    val cache : Cache<K, V> = Caffeine.newBuilder()
        .evictionListener { key: K?, value: V?, _ ->
            if (key != null && value != null) {
                save(key, value)
            }
        }
        .removalListener { key: K?, value: V?, _ ->
            if (key != null && value != null) {
                save(key, value)
            }
        }
        .expireAfterWrite(
            Duration.ofMinutes(5)
        )
        .build<K, V>()
    val waiters : HashMap<K, MutableList<WaiterHandler<V>>> = HashMap()

    abstract fun on(message : ByteArray)

    fun waitReceive(
        key : K,
        target : WaiterHandler<V>
    ) {
        val arr = try {
            waiters[key]!!
        } catch (ex : Exception) {
            mutableListOf()
        }
        arr.add(target)
        waiters[key] = arr
    }

    abstract fun save(key : K, value : V)

    abstract fun get(key : K) : V

}

typealias WaiterHandler<V> = (value : V) -> Unit