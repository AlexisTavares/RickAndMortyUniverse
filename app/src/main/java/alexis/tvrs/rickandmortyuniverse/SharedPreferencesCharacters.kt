package alexis.tvrs.rickandmortyuniverse

import alexis.tvrs.rickandmortyuniverse.ui.characters.Character
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*

class SharedPreferencesCharacters {
    // This four methods are used for maintaining characters.
    fun savecharacters(context: Context, characters: List<Character?>?) {
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

    fun getcharacters(context: Context): ArrayList<Character>? {
        val settings: SharedPreferences
        var characters: List<Character>?
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
        const val PREFS_NAME = "CHARACTER_APP"
        const val CHARACTERS = "CHARACTER"
    }
}