package com.example.photoalbumapp.utils

import com.example.photoalbumapp.domain.model.Album

object AlbumUtils {
    fun getAlbumIds(albums : ArrayList<Album>) : ArrayList<Int?>{
        val result : ArrayList<Int?> = ArrayList()
        for (album in albums){
            result.add(album.id)
        }
        return result
    }
}