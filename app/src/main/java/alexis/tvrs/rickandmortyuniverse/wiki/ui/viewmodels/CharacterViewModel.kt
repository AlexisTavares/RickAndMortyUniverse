package alexis.tvrs.rickandmortyuniverse.wiki.ui.viewmodels

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.rickandmortyapi.RickAndMortyApiDatasource
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CharacterViewModel: ViewModel() {
    var rickAndMortyCharacters = MutableLiveData<List<RickAndMortyCharacter>>()
    var selectedCharacter : RickAndMortyCharacter? = null
    var selectedPage = 1

    fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            rickAndMortyCharacters.postValue(RickAndMortyApiDatasource.fetchRickAndMortyCharacters(selectedPage))
        }
    }

    fun fetchCharacter(id: Int) : MutableLiveData<RickAndMortyCharacter> {
        val character = MutableLiveData<RickAndMortyCharacter>()
        viewModelScope.launch(Dispatchers.IO) {
            character.postValue(RickAndMortyApiDatasource.fetchRickAndMortyCharacter(id))
        }
        return character
    }
}