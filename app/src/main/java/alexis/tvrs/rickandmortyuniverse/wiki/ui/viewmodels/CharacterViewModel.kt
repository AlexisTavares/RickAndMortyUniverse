package alexis.tvrs.rickandmortyuniverse.wiki.ui.viewmodels

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.RickAndMortyDatasource
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class CharacterViewModel: ViewModel() {
    var selectedCharacter = 0
    private var rickAndMortyCharactersLiveData = RickAndMortyDatasource.rickAndMortyCharactersLiveData

    fun fetchCharacters(): LiveData<List<RickAndMortyCharacter>> {
        return RickAndMortyDatasource.fetchRickAndMortyCharacters()
    }

    fun getCharacterToDisplay(): RickAndMortyCharacter? {
        for (character in rickAndMortyCharactersLiveData.value!!) {
            if (character.id == selectedCharacter) {
                return character
            }
        }
        return this.rickAndMortyCharactersLiveData.value?.get(0)
    }
}