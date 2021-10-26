package com.example.photoalbumapp.data.repository

import com.example.photoalbumapp.data.remote.NetworkLayer
import com.example.photoalbumapp.domain.model.Album
import com.example.photoalbumapp.domain.repository.AlbumRepository

class AlbumRepositoryImpl : AlbumRepository {
    override suspend fun getAlbums(userId: Int): ArrayList<Album>? {
        return NetworkLayer.apiClient.getAlbums(userId)
    }
}