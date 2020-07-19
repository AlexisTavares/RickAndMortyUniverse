package alexis.tvrs.rickandmortyuniverse.ui.characters;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AsyncDownloadCharacter extends AsyncTask<Object,Void, Character> {
    URL url;
    ArrayList<Character> list;
    CharacterGridAdapter characterGridAdapter;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Character doInBackground(Object[] objects) {
        Character character = null;
        int characterNb = (int) objects[0];
        list = (ArrayList<Character>) objects[1];
        characterGridAdapter = (CharacterGridAdapter) objects[2];
        String jsonURL = "https://rickandmortyapi.com/api/character/"+characterNb;
        try {
            url = new URL(jsonURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) { InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader input = new BufferedReader(isr);
                String jsonStr = input.readLine();
                JSONObject jsonObject = new JSONObject(jsonStr);

                int id = jsonObject.getInt("id");
                String image = jsonObject.getString("image");
                String name = jsonObject.getString("name");
                String species = jsonObject.getString("species");
                String status = jsonObject.getString("status");
                String gender = jsonObject.getString("gender");
                JSONObject originObject = jsonObject.getJSONObject("origin");
                String origin = originObject.getString("name");
                JSONObject lastLocationObject = jsonObject.getJSONObject("location");
                String lastLocation = lastLocationObject.getString("name");

                character = new Character(id,image,name,status,species,gender,origin,lastLocation);
                character.SetLastLocationUri(lastLocationObject.getString("url"));
                character.SetOriginUri(originObject.getString("url"));
                input.close();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return character;
    }

    @Override
    protected void onPostExecute(Character characterRickMorty) {
        super.onPostExecute(characterRickMorty);
        list.add(characterRickMorty);
        characterGridAdapter.notifyDataSetChanged();
    }
}
