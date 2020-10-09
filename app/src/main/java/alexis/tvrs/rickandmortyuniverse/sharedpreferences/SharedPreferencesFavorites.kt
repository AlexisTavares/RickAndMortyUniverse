package alexis.tvrs.rickandmortyuniverse

import alexis.tvrs.rickandmortyuniverse.ui.characters.RickAndMortyCharacter
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*

class SharedPreferencesFavorites {
    // This four methods are used for maintaining characters.
    fun saveFavorites(context: Context, rickAndMortyCharacters: List<RickAndMortyCharacter?>?) {
        val settings: SharedPreferences
        val editor: SharedPreferences.Editor
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE)
        editor = settings.edit()
        val gson = Gson()
        val jsoncharacters = gson.toJson(rickAndMortyCharacters)
        editor.putString(CHARACTERS, jsoncharacters)
        editor.commit()
    }

    fun addFavorite(context: Context, RickAndMortyCharacter: RickAndMortyCharacter?) {
        var rickAndMortyCharacters: MutableList<RickAndMortyCharacter?>? = getFavorites(context)
        if (rickAndMortyCharacters == null) rickAndMortyCharacters = ArrayList()
        rickAndMortyCharacters.add(RickAndMortyCharacter)
        saveFavorites(context, rickAndMortyCharacters)
    }

    fun removeFavorite(context: Context, RickAndMortyCharacter: RickAndMortyCharacter?) {
        val characters = getFavorites(context)
        if (characters != null) {
            characters.remove(RickAndMortyCharacter)
            saveFavorites(context, characters)
        }
    }

    fun getFavorites(context: Context): ArrayList<RickAndMortyCharacter?>? {
        val settings: SharedPreferences
        var rickAndMortyCharacters: List<RickAndMortyCharacter?>?
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE)
        if (settings.contains(CHARACTERS)) {
            val jsoncharacters = settings.getString(CHARACTERS, null)
            val gson = Gson()
            val characterItems = gson.fromJson(jsoncharacters,
                    Array<RickAndMortyCharacter>::class.java)
            rickAndMortyCharacters = Arrays.asList(*characterItems)
            rickAndMortyCharacters = ArrayList(rickAndMortyCharacters)
        } else return null
        return rickAndMortyCharacters
    }

    companion object {
        const val PREFS_NAME = "FAVORITES_APP"
        const val CHARACTERS = "FAVORITES"
    }
}