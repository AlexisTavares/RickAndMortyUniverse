package alexis.tvrs.rickandmortyuniverse.ui.locations

import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class AsyncDownloadLocation : AsyncTask<Any, Void?, Location?>() {
    var url: URL? = null
    var list: ArrayList<Location?>? = null
    var locationAdapter: LocationAdapter? = null
    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(objects: Array<Any>): Location? {
        var location: Location? = null
        val locationNb = objects[0] as Int
        list = objects[1] as ArrayList<Location?>
        locationAdapter = objects[2] as LocationAdapter
        val jsonURL = "https://rickandmortyapi.com/api/location/$locationNb"
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
                val type = jsonObject.getString("type")
                val dimension = jsonObject.getString("dimension")
                location = Location(id, name, type, dimension)
                input.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return location
    }

    override fun onPostExecute(location: Location?) {
        super.onPostExecute(location)
        list!!.add(location)
        locationAdapter!!.notifyDataSetChanged()
    }
}