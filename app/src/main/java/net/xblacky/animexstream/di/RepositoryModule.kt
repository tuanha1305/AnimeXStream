package net.xblacky.animexstream.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.realm.Realm
import net.xblacky.animexstream.ui.main.home.HomeRepository
import net.xblacky.animexstream.ui.main.player.EpisodeRepository
import net.xblacky.animexstream.ui.main.search.SearchRepository
import retrofit2.Retrofit

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Reusable
    fun provideHomeRepository(retrofit: Retrofit): HomeRepository {
        return HomeRepository(retrofit)
    }

    @Provides
    @Reusable
    fun provideEpisodeRepository(retrofit: Retrofit, realm: Realm): EpisodeRepository {
        return EpisodeRepository(retrofit, realm)
    }

    @Provides
    @Reusable
    fun provideSearchRepository(retrofit: Retrofit): SearchRepository {
        return SearchRepository(retrofit)
    }
}