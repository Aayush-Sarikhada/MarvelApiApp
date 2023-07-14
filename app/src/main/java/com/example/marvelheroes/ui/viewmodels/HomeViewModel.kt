package com.example.marvelheroes.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.models.response.CharacterDetailsResponse
import com.example.marvelheroes.data.repository.MarvelApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiRepository: MarvelApiRepository): ViewModel() {

    private val _charactersResponse = MutableLiveData<CharacterDetailsResponse>()
    var characterResponse = _charactersResponse

    fun getCharactersByName(name: String) {
        viewModelScope.launch {
            val characterResponse = apiRepository.getCharactersByName(name)
            characterResponse?.let {
            _charactersResponse.postValue(it)
            }
        }
    }

}