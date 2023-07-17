package com.example.marvelheroes.data.models.response

import com.google.gson.annotations.SerializedName

data class CharacterDetails(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("description")
    val description: String,
    val isLoadingView: Boolean = false
)