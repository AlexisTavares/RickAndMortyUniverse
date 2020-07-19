package alexis.tvrs.rickandmortyuniverse.ui.episodes;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AsyncDownloadEpisode extends AsyncTask<Object,Void, Episode> {

    URL url;
    ArrayList<Episode> list;
    EpisodeAdapter episodeAdapter;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Episode doInBackground(Object[] objects) {
        Episode episode = null;
        int episodeNb = (int) objects[0];
        list = (ArrayList<Episode>) objects[1];
        episodeAdapter = (EpisodeAdapter) objects[2];
        String jsonURL = "https://rickandmortyapi.com/api/episode/" + episodeNb;
        try {
            url = new URL(jsonURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) { InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader input = new BufferedReader(isr);
                String jsonStr = input.readLine();
                JSONObject jsonObject = new JSONObject(jsonStr);

                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String air_date = jsonObject.getString("air_date");
                String episodeOrder = jsonObject.getString("episode");

                episode = new Episode(id,name,air_date,episodeOrder);
                input.close();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return episode;
    }

    @Override
    protected void onPostExecute(Episode episode) {
        super.onPostExecute(episode);
        list.add(episode);
        episodeAdapter.notifyDataSetChanged();
    }

}
