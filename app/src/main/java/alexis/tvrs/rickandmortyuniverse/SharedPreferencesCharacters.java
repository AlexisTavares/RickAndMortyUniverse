package alexis.tvrs.rickandmortyuniverse;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alexis.tvrs.rickandmortyuniverse.ui.characters.Character;

public class SharedPreferencesCharacters {

    public static final String PREFS_NAME = "CHARACTER_APP";
    public static final String CHARACTERS = "CHARACTER";

    public SharedPreferencesCharacters() {
        super();
    }

    // This four methods are used for maintaining characters.
    public void savecharacters(Context context, List<Character> characters) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsoncharacters = gson.toJson(characters);

        editor.putString(CHARACTERS, jsoncharacters);

        editor.commit();
    }

    public ArrayList<Character> getcharacters(Context context) {
        SharedPreferences settings;
        List<Character> characters;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(CHARACTERS)) {
            String jsoncharacters = settings.getString(CHARACTERS, null);
            Gson gson = new Gson();
            Character[] characterItems = gson.fromJson(jsoncharacters,
                    Character[].class);

            characters = Arrays.asList(characterItems);
            characters = new ArrayList<>(characters);
        } else
            return null;

        return (ArrayList<Character>) characters;
    }
}