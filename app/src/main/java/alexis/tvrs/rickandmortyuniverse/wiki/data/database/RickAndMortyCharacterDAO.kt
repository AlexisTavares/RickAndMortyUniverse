package alexis.tvrs.rickandmortyuniverse.wiki.data.database

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import androidx.room.*

@Dao
@TypeConverters(CharacterTypeConverter::class)
interface RickAndMortyCharacterDAO {
    @Query("SELECT * FROM character")
    fun getAll(): List<RickAndMortyCharacter>

    @Insert
    fun insertAll(characters: List<RickAndMortyCharacter>)

    @Delete
    fun delete(character: RickAndMortyCharacter)
}