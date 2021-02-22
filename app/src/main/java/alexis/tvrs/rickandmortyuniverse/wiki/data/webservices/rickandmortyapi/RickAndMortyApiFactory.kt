package alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.rickandmortyapi

import alexis.tvrs.rickandmortyuniverse.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RickAndMortyApiFactory {
    private const val mBaseUrl = BuildConfig.WSUrl
    private val mRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val rickAndMortyApi: IRickMortyApiEndpoint = mRetrofit.create(IRickMortyApiEndpoint::class.java)
}