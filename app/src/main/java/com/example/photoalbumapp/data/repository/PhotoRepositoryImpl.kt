package com.example.photoalbumapp.data.repository

import com.example.photoalbumapp.data.remote.NetworkLayer
import com.example.photoalbumapp.domain.model.Photo
import com.example.photoalbumapp.domain.repository.PhotoRepository

class PhotoRepositoryImpl : PhotoRepository {
    override suspend fun getPhotos(albumId: Int?): ArrayList<Photo>? {
        return NetworkLayer.apiClient.getPhotos(albumId)
    }
}