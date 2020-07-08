package net.xblacky.animexstream.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.xblacky.animexstream.utils.rertofit.RetrofitHelper
import retrofit2.Retrofit

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit = RetrofitHelper.getRetrofitInstance()
}