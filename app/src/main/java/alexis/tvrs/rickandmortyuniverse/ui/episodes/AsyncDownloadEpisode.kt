package alexis.tvrs.rickandmortyuniverse.ui.episodes

import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class AsyncDownloadEpisode : AsyncTask<Any, Void?, Episode?>() {
    var url: URL? = null
    var list: ArrayList<Episode?>? = null
    var episodeAdapter: EpisodeAdapter? = null
    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(objects: Array<Any>): Episode? {
        var episode: Episode? = null
        val episodeNb = objects[0] as Int
        list = objects[1] as ArrayList<Episode?>
        episodeAdapter = objects[2] as EpisodeAdapter
        val jsonURL = "https://rickandmortyapi.com/api/episode/$episodeNb"
        try {
            url = URL(jsonURL)
            val urlConnection = url!!.openConnection() as HttpURLConnection
            if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val isr = InputStreamReader(urlConnection.inputStream)
                val input = BufferedReader(isr)
                val jsonStr = input.readLine()
                val jsonObject = JSONObject(jsonStr)
                val id = jsonObject.getInt("id")
                val name = jsonObject.getString("name")
                val air_date = jsonObject.getString("air_date")
                val episodeOrder = jsonObject.getString("episode")
                episode = Episode(id, name, air_date, episodeOrder)
                input.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return episode
    }

    override fun onPostExecute(episode: Episode?) {
        super.onPostExecute(episode)
        list!!.add(episode)
        episodeAdapter!!.notifyDataSetChanged()
    }
}