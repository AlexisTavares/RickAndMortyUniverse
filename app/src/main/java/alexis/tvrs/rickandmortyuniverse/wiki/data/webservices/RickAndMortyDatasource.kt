package alexis.tvrs.rickandmortyuniverse.wiki.data.webservices

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.*
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RickAndMortyDatasource {
    var rickAndMortyCharactersLiveData = MutableLiveData<List<RickAndMortyCharacter>>()
    var rickAndMortyEpisodesLiveData = MutableLiveData<List<RickAndMortyEpisode>>()
    var rickAndMortyLocationsLiveData = MutableLiveData<List<RickAndMortyLocation>>()
    private val rickAndMortyApi = RickAndMortyApiFactory.
    rickAndMortyApi

    fun fetchRickAndMortyCharacters(): MutableLiveData<List<RickAndMortyCharacter>>{
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
        return rickAndMortyCharactersLiveData
    }

    fun fetchRickAndMortyCharacters2(): LiveData<List<RickAndMortyCharacter>> {
        val charactersLiveData = MutableLiveData<List<RickAndMortyCharacter>>()

        val rickAndMortyCharactersCall = rickAndMortyApi.getCharacters()
        rickAndMortyCharactersCall.enqueue(object : Callback<RickAndMortyCharactersResponse> {
            override fun onResponse(call: Call<RickAndMortyCharactersResponse>, response: Response<RickAndMortyCharactersResponse>) {
                val statusCode: Int = response.code()
                Log.d("CallCharacterStatus", statusCode.toString())
                charactersLiveData.postValue(response.body()?.results)
            }
            override fun onFailure(call: Call<RickAndMortyCharactersResponse>, t: Throwable) {
                Log.d("getCharacters", t.message.toString())
            }
        })
        return charactersLiveData
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

    fun fetchRickAndMortyCharacter(id: Int): RickAndMortyCharacter? {
        var character:RickAndMortyCharacter? = null

        val rickAndMortyCharactersCall = rickAndMortyApi.getCharacter(id)
        rickAndMortyCharactersCall.enqueue(object : Callback<RickAndMortyCharacter> {
            override fun onResponse(call: Call<RickAndMortyCharacter>, response: Response<RickAndMortyCharacter>) {
                val statusCode: Int = response.code()
                Log.d("CallCharacterStatus", statusCode.toString())
                character = response.body()
            }
            override fun onFailure(call: Call<RickAndMortyCharacter>, t: Throwable) {
                Log.d("getCharacter", t.message.toString())
            }
        })
        return character
    }
}