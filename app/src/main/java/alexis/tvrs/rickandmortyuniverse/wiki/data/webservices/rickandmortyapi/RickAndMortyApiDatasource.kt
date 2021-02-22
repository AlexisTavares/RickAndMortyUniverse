package alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.rickandmortyapi

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.*
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RickAndMortyApiDatasource {
    private val rickAndMortyApi = RickAndMortyApiFactory.rickAndMortyApi

    suspend fun fetchRickAndMortyCharacters(): List<RickAndMortyCharacter> {
        val characters = mutableListOf<RickAndMortyCharacter>()
        val result = rickAndMortyApi.getCharacters()

        result.results?.let {
            characters.addAll(it)
        }

        return characters
    }

    fun fetchRickAndMortyCharacter(id: Int): RickAndMortyCharacter = rickAndMortyApi.getCharacter(id)

}