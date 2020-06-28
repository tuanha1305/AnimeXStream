package net.xblacky.animexstream

import android.app.Application
import net.xblacky.animexstream.utils.realm.InitializeRealm
import timber.log.Timber

class AnimeXStream : Application() {

    override fun onCreate() {
        super.onCreate()
        InitializeRealm.initializeRealm(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}