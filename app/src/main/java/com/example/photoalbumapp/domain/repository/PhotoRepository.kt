package com.example.photoalbumapp.domain.repository

import com.example.photoalbumapp.domain.model.Photo

interface PhotoRepository {
    suspend fun getPhotos(albumId: Int?): ArrayList<Photo>?
}