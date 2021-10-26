package com.example.photoalbumapp.domain.model

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("albumId") val albumId: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("url") val url: String? = null
) {


}