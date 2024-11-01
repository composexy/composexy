package com.composexy.plot

interface Context<K: Context.Key, E> : Map<K, E> {
    operator fun plus(other: Context<K, E>): Context<K, E>
    operator fun minus(other: Context<K, E>): Context<K, E>

    interface Key
}

abstract class AbstractContext<K: Context.Key, E> internal constructor(
    private val elements: Map<K, E>
): Context<K, E>, Map<K, E> by elements
