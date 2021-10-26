package com.example.photoalbumapp.domain.repository

import com.example.photoalbumapp.domain.model.Album

interface AlbumRepository {
    suspend fun getAlbums(userId: Int): ArrayList<Album>?
}