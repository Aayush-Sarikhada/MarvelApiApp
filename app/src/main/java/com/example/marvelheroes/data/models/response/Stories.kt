package com.example.marvelheroes.data.models.response


import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("available")
    val available: String,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("returned")
    val returned: String
)