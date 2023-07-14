package com.example.marvelheroes.data.repository

import com.example.marvelheroes.base.BaseRepository
import com.example.marvelheroes.data.models.response.CharacterDetailsResponse
import com.example.marvelheroes.network.api.MarvelAPI

class MarvelApiRepository(private val apiService: MarvelAPI): BaseRepository() {

    suspend fun getCharactersByName(name: String): CharacterDetailsResponse? {
        val response = apiService.getCharacterByName(name)
        return handleResponse(response).getOrNull()
    }

}