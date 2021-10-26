package com.example.photoalbumapp.di

import com.example.photoalbumapp.domain.use_cases.GetAlbumsUseCase
import com.example.photoalbumapp.domain.use_cases.GetPhotosUseCase
import com.example.photoalbumapp.presentation.SharedViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun providesSharedViewModelFactory(
        getAlbumsUseCase: GetAlbumsUseCase,
        getPhotosUseCase: GetPhotosUseCase
    ): SharedViewModelFactory {
        return SharedViewModelFactory(getAlbumsUseCase, getPhotosUseCase)
    }
}