package com.example.photoalbumapp.di

import com.example.photoalbumapp.data.remote.ApiClient
import com.example.photoalbumapp.data.remote.ApiService
import com.example.photoalbumapp.data.repository.AlbumRepositoryImpl
import com.example.photoalbumapp.data.repository.PhotoRepositoryImpl
import com.example.photoalbumapp.domain.repository.AlbumRepository
import com.example.photoalbumapp.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL: String = "https://jsonplaceholder.typicode.com/"

@Module
object AppModule {

    @Provides
    @Singleton
    fun providesApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesApiClient(api: ApiService): ApiClient {
        return ApiClient(api)
    }

    @Provides
    @Singleton
    fun provideAlbumRepository(apiClient: ApiClient): AlbumRepository {
        return AlbumRepositoryImpl(apiClient)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(apiClient: ApiClient): PhotoRepository {
        return PhotoRepositoryImpl(apiClient)
    }
}