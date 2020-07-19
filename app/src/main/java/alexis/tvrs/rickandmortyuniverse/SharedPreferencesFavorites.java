package alexis.tvrs.rickandmortyuniverse;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alexis.tvrs.rickandmortyuniverse.ui.characters.Character;

public class SharedPreferencesFavorites {

    public static final String PREFS_NAME = "FAVORITES_APP";
    public static final String CHARACTERS = "FAVORITES";

    public SharedPreferencesFavorites() {
        super();
    }

    // This four methods are used for maintaining characters.
    public void saveFavorites(Context context, List<Character> characters) {
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

    public void addFavorite(Context context, Character Character) {
        List<Character> characters = getFavorites(context);
        if (characters == null)
            characters = new ArrayList<>();
        characters.add(Character);
        saveFavorites(context, characters);
    }

    public void removeFavorite(Context context, Character Character) {
        ArrayList<Character> characters = getFavorites(context);
        if (characters != null) {
            characters.remove(Character);
            saveFavorites(context, characters);
        }
    }

    public ArrayList<Character> getFavorites(Context context) {
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