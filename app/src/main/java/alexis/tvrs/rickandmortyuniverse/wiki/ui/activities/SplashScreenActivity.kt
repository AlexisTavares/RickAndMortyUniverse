package alexis.tvrs.rickandmortyuniverse.wiki.ui.activities

import alexis.tvrs.rickandmortyuniverse.wiki.models.webservices.IRickMortyApiEndpoint
import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.sharedpreferences.SharedPreferencesFavorites
import alexis.tvrs.rickandmortyuniverse.wiki.models.webservices.RickAndMortyApiService
import alexis.tvrs.rickandmortyuniverse.wiki.models.webservices.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.models.webservices.Episode
import alexis.tvrs.rickandmortyuniverse.wiki.models.webservices.Location
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreenActivity : Activity() {
    private val mApiService: IRickMortyApiEndpoint = RickAndMortyApiService.retrofit.create(IRickMortyApiEndpoint::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val favorites = SharedPreferencesFavorites.getFavorites(this)
        if (favorites!= null)

            FAVORITES.addAll(favorites)
        var rickAndMortyCharacterCall: Call<RickAndMortyCharacter>
        for (character_id in 1 .. NBCHARACTERS){
            rickAndMortyCharacterCall = mApiService.getCharacter(character_id)
            rickAndMortyCharacterCall.enqueue(object : Callback<RickAndMortyCharacter> {
                override fun onResponse(call: Call<RickAndMortyCharacter>, response: Response<RickAndMortyCharacter>) {
                    val statusCode: Int = response.code()
                    Log.d("CallCharacterStatus", statusCode.toString())
                    val character: RickAndMortyCharacter? = response.body()
                    if (character != null) {
                        CHARACTERS.add(character)
                                .also { CHARACTERS.sortBy { it.id } }
                    }
                }
                override fun onFailure(call: Call<RickAndMortyCharacter>, t: Throwable) {
                    Log.d("getCharacter", t.message.toString())
                }
            })
        }

        var episodeCall: Call<Episode>
        for (episode_id in 1 .. NBEPISODES){
            episodeCall = mApiService.getEpisode(episode_id)
            episodeCall.enqueue(object : Callback<Episode> {
                override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                    val statusCode: Int = response.code()
                    Log.d("CallCharacterStatus", statusCode.toString())
                    val episode: Episode? = response.body()
                    if (episode != null) {
                        EPISODES.add(episode)
                                .also { EPISODES.sortBy { it.episode } }
                    }
                }
                override fun onFailure(call: Call<Episode>, t: Throwable) {
                    Log.d("getEpisode", t.message.toString())
                }
            })
        }

        var locationCall: Call<Location>
        for (location_id in 1 .. NBLOCATIONS){
            locationCall = mApiService.getLocation(location_id)
            locationCall.enqueue(object : Callback<Location> {
                override fun onResponse(call: Call<Location>, response: Response<Location>) {
                    val statusCode: Int = response.code()
                    Log.d("CallCharacterStatus", statusCode.toString())
                    val location: Location? = response.body()
                    if (location != null) {
                        LOCATIONS.add(location)
                    }
                }
                override fun onFailure(call: Call<Location>, t: Throwable) {
                    Log.d("getLocation", t.message.toString())
                }
            })                                .also { LOCATIONS.sortBy { it.name } }

        }

        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val NBCHARACTERS = 493
        val CHARACTERS = ArrayList<RickAndMortyCharacter>()
        var FAVORITES = ArrayList<RickAndMortyCharacter>()

        private const val NBLOCATIONS = 76
        val LOCATIONS = ArrayList<Location>()

        private const val NBEPISODES = 31
        val EPISODES = ArrayList<Episode>()
    }


}