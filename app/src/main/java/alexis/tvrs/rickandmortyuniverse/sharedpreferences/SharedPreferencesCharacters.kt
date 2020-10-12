package alexis.tvrs.rickandmortyuniverse.sharedpreferences

import alexis.tvrs.rickandmortyuniverse.wiki.models.RickAndMortyCharacter
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*

class SharedPreferencesCharacters {
    // This four methods are used for maintaining characters.
    fun savecharacters(context: Context, rickAndMortyCharacters: List<RickAndMortyCharacter?>?) {
        val settings: SharedPreferences
        val editor: SharedPreferences.Editor
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE)
        editor = settings.edit()
        val gson = Gson()
        val jsoncharacters = gson.toJson(rickAndMortyCharacters)
        editor.putString(SHARED_CHARACTERS, jsoncharacters)
        editor.apply()
    }

    fun getcharacters(context: Context): ArrayList<RickAndMortyCharacter>? {
        var rickAndMortyCharacters: List<RickAndMortyCharacter>?
        val settings: SharedPreferences = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE)
        if (settings.contains(SHARED_CHARACTERS)) {
            val jsoncharacters = settings.getString(SHARED_CHARACTERS, null)
            val gson = Gson()
            val characterItems = gson.fromJson(jsoncharacters,
                    Array<RickAndMortyCharacter>::class.java)
            rickAndMortyCharacters = Arrays.asList(*characterItems)
            rickAndMortyCharacters = ArrayList(rickAndMortyCharacters)
        } else return null
        return rickAndMortyCharacters
    }

    companion object {
        const val PREFS_NAME = "CHARACTER_APP"
        const val SHARED_CHARACTERS = "CHARACTER"
    }
}