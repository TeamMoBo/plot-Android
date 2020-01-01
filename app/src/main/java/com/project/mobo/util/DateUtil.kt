package com.project.mobo.util

import android.content.Context
import android.os.Handler
import java.util.*

private const val SEC = 1000
private const val MIN = 60 * SEC

/**
 * 분 단위로 넘어옴
 * */
fun Date.diff(other: Date): Float {
    return (this.time - other.time) / MIN.toFloat()
}

/**
 * 분 단위로 넘어옴
 * */
fun Date.distance(): Float {
    return Date().diff(this)
}

fun Context.registerEvent(min: Float, action: () -> Unit) {
    Handler(mainLooper).postDelayed(
        { action() },
        (min * MIN).toLong()
    )
}

fun Context.registerEvent(pivot: Date, min: Float, action: () -> Unit) {
    Handler(mainLooper).postDelayed(
        { action() },
        (min * MIN - pivot.distance()).toLong()
    )
}