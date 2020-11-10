package alexis.tvrs.rickandmortyuniverse.wiki.data.repositories

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.*
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.RickAndMortyApiService
import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RickAndMortyRepository {
    var rickAndMortyCharactersLiveData = MutableLiveData<List<RickAndMortyCharacter>>()
    var rickAndMortyEpisodesLiveData = MutableLiveData<List<RickAndMortyEpisode>>()
    var rickAndMortyLocationsLiveData = MutableLiveData<List<RickAndMortyLocation>>()
    private val rickAndMortyApi = RickAndMortyApiService.rickAndMortyApi

    fun fetchRickAndMortyCharacters(){
            val rickAndMortyCharactersCall = rickAndMortyApi.getCharacters()
            rickAndMortyCharactersCall.enqueue(object : Callback<RickAndMortyCharactersResponse> {
                override fun onResponse(call: Call<RickAndMortyCharactersResponse>, response: Response<RickAndMortyCharactersResponse>) {
                    val statusCode: Int = response.code()
                    Log.d("CallCharacterStatus", statusCode.toString())
                    rickAndMortyCharactersLiveData.postValue(response.body()?.results)
                }
                override fun onFailure(call: Call<RickAndMortyCharactersResponse>, t: Throwable) {
                    Log.d("getCharacters", t.message.toString())
                }
            })
    }

    fun fetchRickAndMortyEpisodes(): MutableLiveData<List<RickAndMortyEpisode>> {
        val rickAndMortyEpisodesCall = rickAndMortyApi.getEpisodes()
        rickAndMortyEpisodesCall.enqueue(object : Callback<RickAndMortyEpisodesResponse> {
            override fun onResponse(call: Call<RickAndMortyEpisodesResponse>, response: Response<RickAndMortyEpisodesResponse>) {
                val statusCode: Int = response.code()
                Log.d("CallEpisodesStatus", statusCode.toString())
                rickAndMortyEpisodesLiveData.postValue(response.body()?.results)
            }
            override fun onFailure(call: Call<RickAndMortyEpisodesResponse>, t: Throwable) {
                Log.d("getEpisodes", t.message.toString())
            }
        })
        return rickAndMortyEpisodesLiveData
    }

    fun fetchRickAndMortyLocations(): MutableLiveData<List<RickAndMortyLocation>> {
        val rickAndMortyLocationsCall = rickAndMortyApi.getLocations()
        rickAndMortyLocationsCall.enqueue(object : Callback<RickAndMortyLocationsResponse> {
            override fun onResponse(call: Call<RickAndMortyLocationsResponse>, response: Response<RickAndMortyLocationsResponse>) {
                val statusCode: Int = response.code()
                Log.d("CallLocationsStatus", statusCode.toString())
                rickAndMortyLocationsLiveData.postValue(response.body()?.results)
            }
            override fun onFailure(call: Call<RickAndMortyLocationsResponse>, t: Throwable) {
                Log.d("getLocations", t.message.toString())
            }
        })
        return rickAndMortyLocationsLiveData
    }
}