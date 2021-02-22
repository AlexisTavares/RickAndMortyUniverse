package alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.rickandmortyapi

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IRickMortyApiEndpoint {
    @GET("character/{character_id}")
    fun getCharacter(@Path("character_id") character_id: Int): RickAndMortyCharacter

    @GET("character")
    suspend fun getCharacters(): RickAndMortyCharactersResponse
}