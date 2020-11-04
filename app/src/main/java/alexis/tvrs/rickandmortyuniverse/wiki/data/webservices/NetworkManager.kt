package alexis.tvrs.rickandmortyuniverse.wiki.data.webservices

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyEpisode
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyLocation
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NetworkManager {
    val mApiService = RickAndMortyApiService.retrofit.create(IRickMortyApiEndpoint::class.java)
    private const val NBCHARACTERS = 493
    private const val NBEPISODES = 31
    private const val NBLOCATIONS = 76

    fun getCharacters(): ArrayList<RickAndMortyCharacter>{
        val characterList = ArrayList<RickAndMortyCharacter>()
        var rickAndMortyCharacterCall: Call<RickAndMortyCharacter>
        for (character_id in 1 .. NBCHARACTERS){
            rickAndMortyCharacterCall = mApiService.getCharacter(character_id)
            rickAndMortyCharacterCall.enqueue(object : Callback<RickAndMortyCharacter> {
                override fun onResponse(call: Call<RickAndMortyCharacter>, response: Response<RickAndMortyCharacter>) {
                    val statusCode: Int = response.code()
                    Log.d("CallCharacterStatus", statusCode.toString())
                    val character: RickAndMortyCharacter? = response.body()
                    if (character != null) {
                        characterList.add(character)
                                .also { characterList.sortBy { it.id } }
                    }
                }
                override fun onFailure(call: Call<RickAndMortyCharacter>, t: Throwable) {
                    Log.d("getCharacter", t.message.toString())
                }
            })
        }
        return characterList
    }

    fun getEpisodes(): ArrayList<RickAndMortyEpisode>{
        val episodeList = ArrayList<RickAndMortyEpisode>()
        var rickAndMortyEpisodeCall: Call<RickAndMortyEpisode>
        for (episode_id in 1 .. NBEPISODES){
            rickAndMortyEpisodeCall = mApiService.getEpisode(episode_id)
            rickAndMortyEpisodeCall.enqueue(object : Callback<RickAndMortyEpisode> {
                override fun onResponse(call: Call<RickAndMortyEpisode>, response: Response<RickAndMortyEpisode>) {
                    val statusCode: Int = response.code()
                    Log.d("CallCharacterStatus", statusCode.toString())
                    val rickAndMortyEpisode: RickAndMortyEpisode? = response.body()
                    if (rickAndMortyEpisode != null) {
                        episodeList.add(rickAndMortyEpisode)
                                .also { episodeList.sortBy { it.episode } }
                    }
                }
                override fun onFailure(call: Call<RickAndMortyEpisode>, t: Throwable) {
                    Log.d("getEpisode", t.message.toString())
                }
            })
        }
        return episodeList
    }

    fun getLocations(): ArrayList<RickAndMortyLocation>{
        val locationList = ArrayList<RickAndMortyLocation>()
        var rickAndMortyLocationCall: Call<RickAndMortyLocation>
        for (location_id in 1 .. NBLOCATIONS){
            rickAndMortyLocationCall = mApiService.getLocation(location_id)
            rickAndMortyLocationCall.enqueue(object : Callback<RickAndMortyLocation> {
                override fun onResponse(call: Call<RickAndMortyLocation>, response: Response<RickAndMortyLocation>) {
                    val statusCode: Int = response.code()
                    Log.d("CallCharacterStatus", statusCode.toString())
                    val rickAndMortyLocation: RickAndMortyLocation? = response.body()
                    if (rickAndMortyLocation != null) {
                        locationList.add(rickAndMortyLocation)
                                .also { locationList.sortBy { it.name } }
                    }
                }
                override fun onFailure(call: Call<RickAndMortyLocation>, t: Throwable) {
                    Log.d("getLocation", t.message.toString())
                }
            })
        }
        return locationList
    }
}