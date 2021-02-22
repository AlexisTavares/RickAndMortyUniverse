package alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.rickandmortyapi

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.*

object RickAndMortyApiDatasource {
    private val rickAndMortyApi = RickAndMortyApiFactory.rickAndMortyApi

    suspend fun fetchRickAndMortyCharacters(page: Int): List<RickAndMortyCharacter> {
        val characters = mutableListOf<RickAndMortyCharacter>()
        val result = rickAndMortyApi.getCharacters(page)

        result.results?.let {
            characters.addAll(it)
        }

        return characters
    }

    fun fetchRickAndMortyCharacter(id: Int): RickAndMortyCharacter = rickAndMortyApi.getCharacter(id)

}