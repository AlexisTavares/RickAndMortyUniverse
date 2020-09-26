package alexis.tvrs.rickandmortyuniverse

import alexis.tvrs.rickandmortyuniverse.ui.characters.Character
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*

class SharedPreferencesFavorites {
    // This four methods are used for maintaining characters.
    fun saveFavorites(context: Context, characters: List<Character?>?) {
        val settings: SharedPreferences
        val editor: SharedPreferences.Editor
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE)
        editor = settings.edit()
        val gson = Gson()
        val jsoncharacters = gson.toJson(characters)
        editor.putString(CHARACTERS, jsoncharacters)
        editor.commit()
    }

    fun addFavorite(context: Context, Character: Character?) {
        var characters: MutableList<Character?>? = getFavorites(context)
        if (characters == null) characters = ArrayList()
        characters.add(Character)
        saveFavorites(context, characters)
    }

    fun removeFavorite(context: Context, Character: Character?) {
        val characters = getFavorites(context)
        if (characters != null) {
            characters.remove(Character)
            saveFavorites(context, characters)
        }
    }

    fun getFavorites(context: Context): ArrayList<Character?>? {
        val settings: SharedPreferences
        var characters: List<Character?>?
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE)
        if (settings.contains(CHARACTERS)) {
            val jsoncharacters = settings.getString(CHARACTERS, null)
            val gson = Gson()
            val characterItems = gson.fromJson(jsoncharacters,
                    Array<Character>::class.java)
            characters = Arrays.asList(*characterItems)
            characters = ArrayList(characters)
        } else return null
        return characters
    }

    companion object {
        const val PREFS_NAME = "FAVORITES_APP"
        const val CHARACTERS = "FAVORITES"
    }
}