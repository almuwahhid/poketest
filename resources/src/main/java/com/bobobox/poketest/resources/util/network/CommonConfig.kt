package com.bobobox.poketest.resources.util.network

import com.bobobox.poketest.resources.util.ext.getMessage
import com.tokopedia.core.util.network.Common
import retrofit2.Response

private fun Response<*>.getError(): Pair<Int, String> {
    return Pair(code(), code().getMessage())
}

// TODO : parsing tobe a Common class
fun <T> Response<T>.parse(): Common<T, Pair<Int, String>> {
    return if (isSuccessful) {
        //Error code from 200 - 299
        body()?.let {
            Common.Left(it)
        } ?: kotlin.run {
            Common.Right(getError())
        }
    } else {
        //Error code from 400 - 599
        Common.Right(getError())
    }
}