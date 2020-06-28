package net.xblacky.animexstream.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.realm.Realm
import net.xblacky.animexstream.utils.realm.InitializeRealm

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideRealm(): Realm {
        return Realm.getInstance(InitializeRealm.getConfig())
    }
}