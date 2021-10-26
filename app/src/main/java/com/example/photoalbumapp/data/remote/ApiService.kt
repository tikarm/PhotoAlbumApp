package com.example.photoalbumapp.data.remote

import com.example.photoalbumapp.domain.model.Album
import com.example.photoalbumapp.domain.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("albums/")
    suspend fun getAlbums(@Query("userId") userId: Int) : ArrayList<Album>

    @GET("photos/")
    suspend fun getPhotos(@Query("albumId") albumId: Int?) : ArrayList<Photo>
}