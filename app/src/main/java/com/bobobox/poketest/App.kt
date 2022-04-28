package com.bobobox.poketest

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.bobobox.poketest.app.di.viewModelModule
import com.bobobox.poketest.di.databaseModule
import com.bobobox.poketest.di.remoteModule
import com.bobobox.poketest.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@App)
            // TODO : Rules = View -> ViewModel -> Repo -> Source(Offline/Online)
            modules(
                listOf(
                    remoteModule,
                    databaseModule,
                    repoModule,
                    viewModelModule,
                )
            )
        }
//        Timber.plant(DebugTree("_poketest"))
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}