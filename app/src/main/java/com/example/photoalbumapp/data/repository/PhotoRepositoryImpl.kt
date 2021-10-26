package com.example.photoalbumapp.data.repository

import com.example.photoalbumapp.data.remote.ApiClient
import com.example.photoalbumapp.domain.model.Photo
import com.example.photoalbumapp.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val apiClient: ApiClient) : PhotoRepository {
    override suspend fun getPhotos(albumId: Int?): ArrayList<Photo>? {
        return apiClient.getPhotos(albumId)
    }
}