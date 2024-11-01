package com.composexy.plot.input

import com.composexy.plot.AbstractContext
import com.composexy.plot.Context

class InputKey(
    val attribute: String
) : Context.Key {

    override fun equals(other: Any?): Boolean =
        this === other || other is InputKey && other.attribute == attribute

    override fun hashCode(): Int = attribute.hashCode()
}

class InputContext(
    private val frames: Map<InputKey, List<*>>
) : AbstractContext<InputKey, List<*>>(frames) {
    override fun plus(other: Context<InputKey, List<*>>): Context<InputKey, List<*>> {
        return when {
            other.isEmpty() -> this
            this.isEmpty() -> other
            else -> InputContext(frames = frames + other)
        }
    }

    override fun minus(other: Context<InputKey, List<*>>): Context<InputKey, List<*>> {
        val intersect = frames.keys.intersect(other.keys)
        return when {
            intersect.isEmpty() -> this
            else -> InputContext(frames = frames - intersect)
        }
    }
}

fun key(attribute: String) = InputKey(attribute)
