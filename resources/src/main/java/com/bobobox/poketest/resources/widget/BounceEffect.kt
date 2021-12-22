package com.bobobox.poketest.resources.widget

class BounceEffect(amplitudo : Double, freq : Double) : android.view.animation.Interpolator {
    private var mAmplitude = 1.0
    private var mFrequency = 10.0

    init {
        mAmplitude = amplitudo
        mFrequency = freq
    }

    override fun getInterpolation(p0: Float): Float {
        return (-1 * Math.pow(Math.E, -p0 / mAmplitude) *
                Math.cos(mFrequency * p0) + 1).toFloat()
    }
}