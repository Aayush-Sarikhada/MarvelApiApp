package com.example.marvelheroes.network.api

import com.example.marvelheroes.common.Constants
import com.example.marvelheroes.data.models.response.CharacterDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {

    @GET("/v1/public/characters")
    suspend fun getCharacterByName(
        @Query("name") name: String,
        @Query("apikey") apikey: String = Constants.MarvelApiConstants.MARVEL_KEY_PUBLIC,
        @Query("ts") timeStamp: String = Constants.MarvelApiConstants.timeStamp,
        @Query("hash") hash: String = Constants.MarvelApiConstants.hash()
    ): Response<CharacterDetailsResponse>

}