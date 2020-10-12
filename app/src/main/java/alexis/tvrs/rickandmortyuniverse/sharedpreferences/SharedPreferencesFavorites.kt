package alexis.tvrs.rickandmortyuniverse.sharedpreferences

import alexis.tvrs.rickandmortyuniverse.wiki.models.RickAndMortyCharacter
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*

class SharedPreferencesFavorites {

    companion object {
        private const val PREFS_NAME = "FAVORITES_APP"
        private const val CHARACTERS = "FAVORITES"

        fun saveFavorites(context: Context, rickAndMortyCharacters: List<RickAndMortyCharacter?>?) {
            val settings: SharedPreferences
            val editor: SharedPreferences.Editor
            settings = context.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE)
            editor = settings.edit()
            val gson = Gson()
            val jsoncharacters = gson.toJson(rickAndMortyCharacters)
            editor.putString(CHARACTERS, jsoncharacters)
            editor.apply()
        }

        fun getFavorites(context: Context): ArrayList<RickAndMortyCharacter>? {
            var rickAndMortyCharacters: List<RickAndMortyCharacter>?
            val settings: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            if (settings.contains(CHARACTERS)) {
                val jsoncharacters = settings.getString(CHARACTERS, null)
                val gson = Gson()
                val characterItems = gson.fromJson(jsoncharacters,
                        Array<RickAndMortyCharacter>::class.java)
                rickAndMortyCharacters = listOf(*characterItems)
                rickAndMortyCharacters = ArrayList(rickAndMortyCharacters)
            } else return null
            return rickAndMortyCharacters
        }
    }
}