package com.tokopedia.core.util.network

sealed class Common<out L, out R> {
    class Left<out L>(val left: L) : Common<L, Nothing>()
    class Right<out R>(val right: R) : Common<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    // TODO : to spare between success and fail (success = left, fail = right)
    inline fun <V>fold(fnL: (L) -> V, fnR: (R) -> V): V =
        when (this) {
            is Left -> fnL(left)
            is Right -> fnR(right)
    }
}