package com.example.photoalbumapp.data.repository

import com.example.photoalbumapp.data.remote.ApiClient
import com.example.photoalbumapp.domain.model.Album
import com.example.photoalbumapp.domain.repository.AlbumRepository
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(private val apiClient: ApiClient) : AlbumRepository {
    override suspend fun getAlbums(userId: Int): ArrayList<Album>? {
        return apiClient.getAlbums(userId)
    }
}