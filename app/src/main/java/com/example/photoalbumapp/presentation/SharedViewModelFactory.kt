package com.example.photoalbumapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photoalbumapp.data.remote.ApiService
import com.example.photoalbumapp.data.repository.AlbumRepositoryImpl
import com.example.photoalbumapp.data.repository.PhotoRepositoryImpl
import com.example.photoalbumapp.domain.use_cases.GetAlbumsUseCase
import com.example.photoalbumapp.domain.use_cases.GetPhotosUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedViewModelFactory @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val getPhotosUseCase: GetPhotosUseCase) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(getAlbumsUseCase, getPhotosUseCase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}