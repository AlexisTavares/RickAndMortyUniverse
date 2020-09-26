package alexis.tvrs.rickandmortyuniverse.ui.characters

import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class AsyncDownloadCharacter : AsyncTask<Any, Void?, Character?>() {
    var url: URL? = null
    var list: ArrayList<Character?>? = null
    var characterGridAdapter: CharacterGridAdapter? = null
    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(objects: Array<Any>): Character? {
        var character: Character? = null
        val characterNb = objects[0] as Int
        list = objects[1] as ArrayList<Character?>
        characterGridAdapter = objects[2] as CharacterGridAdapter
        val jsonURL = "https://rickandmortyapi.com/api/character/$characterNb"
        try {
            url = URL(jsonURL)
            val urlConnection = url!!.openConnection() as HttpURLConnection
            if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val isr = InputStreamReader(urlConnection.inputStream)
                val input = BufferedReader(isr)
                val jsonStr = input.readLine()
                val jsonObject = JSONObject(jsonStr)
                val id = jsonObject.getInt("id")
                val image = jsonObject.getString("image")
                val name = jsonObject.getString("name")
                val species = jsonObject.getString("species")
                val status = jsonObject.getString("status")
                val gender = jsonObject.getString("gender")
                val originObject = jsonObject.getJSONObject("origin")
                val origin = originObject.getString("name")
                val lastLocationObject = jsonObject.getJSONObject("location")
                val lastLocation = lastLocationObject.getString("name")
                character = Character(id, image, name, status, species, gender, origin, lastLocation)
                character.SetLastLocationUri(lastLocationObject.getString("url"))
                character.SetOriginUri(originObject.getString("url"))
                input.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return character
    }

    override fun onPostExecute(characterRickMorty: Character?) {
        super.onPostExecute(characterRickMorty)
        list!!.add(characterRickMorty)
        characterGridAdapter!!.notifyDataSetChanged()
    }
}