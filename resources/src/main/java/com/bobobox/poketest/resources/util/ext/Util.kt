package com.bobobox.poketest.resources.util.ext

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.bobobox.poketest.resources.R
import com.bobobox.poketest.resources.widget.BounceEffect
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> String.toData(): T {
    return Gson().fromJson(this, object: TypeToken<T>(){}.type)
}

fun Any.toJson(): String {
    return Gson().toJson(this)
}

fun View.bounceEffect(context: Context){
    var anim = AnimationUtils.loadAnimation(context, R.anim.bounce_in)
    anim.setInterpolator(BounceEffect(0.2, 20.0))
    startAnimation(anim)
}

fun Context.ToastShort(text: String?) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}