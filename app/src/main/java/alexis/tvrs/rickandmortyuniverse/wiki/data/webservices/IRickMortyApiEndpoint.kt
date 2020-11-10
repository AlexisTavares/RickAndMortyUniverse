package alexis.tvrs.rickandmortyuniverse.wiki.data.webservices

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IRickMortyApiEndpoint {
    @GET("character/{character_id}")
    fun getCharacter(@Path("character_id") character_id: Int): Call<RickAndMortyCharacter>

    @GET("character")
    fun getCharacters(): Call<RickAndMortyCharactersResponse>

    @GET("location/{location_id}")
    fun getLocation(@Path("location_id") location_id: Int): Call<RickAndMortyLocation>

    @GET("location")
    fun getLocations(): Call<RickAndMortyLocationsResponse>

    @GET("episode/{episode_id}")
    fun getEpisode(@Path("episode_id") episode_id: Int): Call<RickAndMortyEpisode>

    @GET("episode")
    fun getEpisodes(): Call<RickAndMortyEpisodesResponse>
}