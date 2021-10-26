package com.example.photoalbumapp.data.remote

import com.example.photoalbumapp.domain.model.Album
import com.example.photoalbumapp.domain.model.Photo

class ApiClient(private val apiService: ApiService) {

    suspend fun getAlbums(userId: Int): ArrayList<Album> {
        return apiService.getAlbums(userId)
    }

    suspend fun getPhotos(albumId: Int?): ArrayList<Photo> {
        return apiService.getPhotos(albumId)
    }
}