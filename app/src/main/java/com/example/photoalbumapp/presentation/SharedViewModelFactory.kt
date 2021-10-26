package com.example.photoalbumapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photoalbumapp.domain.use_cases.GetAlbumsUseCase
import com.example.photoalbumapp.domain.use_cases.GetPhotosUseCase

class SharedViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(GetAlbumsUseCase(), GetPhotosUseCase()) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}