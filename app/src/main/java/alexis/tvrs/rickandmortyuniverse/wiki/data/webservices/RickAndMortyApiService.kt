package alexis.tvrs.rickandmortyuniverse.wiki.data.webservices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RickAndMortyApiService {
    private const val mBaseUrl = "https://rickandmortyapi.com/api/"
    private val mRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val rickAndMortyApi: IRickMortyApiEndpoint = mRetrofit.create(IRickMortyApiEndpoint::class.java)
}