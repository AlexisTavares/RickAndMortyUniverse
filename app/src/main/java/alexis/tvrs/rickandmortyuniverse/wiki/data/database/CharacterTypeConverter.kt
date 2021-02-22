package alexis.tvrs.rickandmortyuniverse.wiki.data.database

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.rickandmortyapi.RickAndMortyApiDatasource
import androidx.room.TypeConverter

class CharacterTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromInstant(value: RickAndMortyCharacter): Int {
            return value.id
        }

        @TypeConverter
        @JvmStatic
        fun toRickAndMortyCharacter(value: Int): RickAndMortyCharacter? {
            return RickAndMortyApiDatasource.fetchRickAndMortyCharacter(value)
        }
    }
}