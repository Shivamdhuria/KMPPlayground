package me.abc.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.abc.library.network.DogService
import me.abc.library.network.DogServiceImpl
import me.abc.library.network.models.DogDtoMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRecipeService(): DogService {
        return DogServiceImpl()
    }

    @Singleton
    @Provides
    fun provideRecipeMapper(): DogDtoMapper {
        return DogDtoMapper()
    }


}