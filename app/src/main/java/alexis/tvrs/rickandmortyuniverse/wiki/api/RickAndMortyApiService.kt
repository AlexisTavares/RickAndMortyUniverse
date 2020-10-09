package alexis.tvrs.rickandmortyuniverse.wiki.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickAndMortyApiService {

    companion object {
        val BASE_URL = "https://rickandmortyapi.com/api/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}