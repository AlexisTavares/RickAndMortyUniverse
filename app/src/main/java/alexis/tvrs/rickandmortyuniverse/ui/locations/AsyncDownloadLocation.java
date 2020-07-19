package alexis.tvrs.rickandmortyuniverse.ui.locations;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AsyncDownloadLocation extends AsyncTask<Object,Void, Location> {
    URL url;
    ArrayList<Location> list;
    LocationAdapter locationAdapter;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Location doInBackground(Object[] objects) {
        Location location = null;
        int locationNb = (int) objects[0];
        list = (ArrayList<Location>) objects[1];
        locationAdapter = (LocationAdapter) objects[2];
        String jsonURL = "https://rickandmortyapi.com/api/location/" + locationNb;
        try {
            url = new URL(jsonURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader input = new BufferedReader(isr);
                String jsonStr = input.readLine();
                JSONObject jsonObject = new JSONObject(jsonStr);

                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String type = jsonObject.getString("type");
                String dimension = jsonObject.getString("dimension");

                location = new Location(id,name,type,dimension);
                input.close();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return location;
    }

    @Override
    protected void onPostExecute(Location location) {
        super.onPostExecute(location);
        list.add(location);
        locationAdapter.notifyDataSetChanged();
    }
}
