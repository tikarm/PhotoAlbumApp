package com.example.photoalbumapp.domain.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("userId") val userId: Int? = null,
    @SerializedName("title") val title: String? = null,
    var photoList: ArrayList<Photo>? = null
) {


}