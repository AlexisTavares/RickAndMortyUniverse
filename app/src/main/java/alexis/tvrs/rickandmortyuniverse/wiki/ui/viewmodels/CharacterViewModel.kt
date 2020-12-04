package alexis.tvrs.rickandmortyuniverse.wiki.ui.viewmodels

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.RickAndMortyDatasource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class CharacterViewModel: ViewModel() {
    var selectedCharacter = 0
    val rickAndMortyCharactersLiveData = MutableLiveData<List<RickAndMortyCharacter>>()
    var characterList = mutableListOf<RickAndMortyCharacter>()

    fun fetchCharacters(): LiveData<List<RickAndMortyCharacter>> {
        return RickAndMortyDatasource.fetchRickAndMortyCharacters()
    }

    fun fetchCharacters2() {
        if(characterList.size != 0 ) {
            rickAndMortyCharactersLiveData.postValue(characterList)
        } else {
            Transformations.map(RickAndMortyDatasource.fetchRickAndMortyCharacters()) {
                characterList = it.toMutableList()
                rickAndMortyCharactersLiveData.postValue(it)
            }
        }
    }
    fun getCharacterToDisplay(): RickAndMortyCharacter? {
        return RickAndMortyDatasource.rickAndMortyCharactersLiveData.value?.get(selectedCharacter)
    }
}