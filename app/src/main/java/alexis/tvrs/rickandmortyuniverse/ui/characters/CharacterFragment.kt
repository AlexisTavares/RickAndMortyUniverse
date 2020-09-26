package alexis.tvrs.rickandmortyuniverse.ui.characters

import alexis.tvrs.rickandmortyuniverse.NetworkUtil.getConnectivityStatus
import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.SharedPreferencesCharacters
import alexis.tvrs.rickandmortyuniverse.SharedPreferencesFavorites
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.util.*

class CharacterFragment : Fragment() {
    private var characters: ArrayList<Character>? = ArrayList()
    private var characterGridAdapter: CharacterGridAdapter? = null
    private var sharedPreferencesFavorites: SharedPreferencesFavorites? = null
    private var sharedPreferencesCharacters: SharedPreferencesCharacters? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_characters, container, false)
        sharedPreferencesFavorites = SharedPreferencesFavorites()
        sharedPreferencesCharacters = SharedPreferencesCharacters()
        val simpleGrid = root.findViewById<GridView>(R.id.fragment_characters_simpleGridView)
        characterGridAdapter = CharacterGridAdapter(this.context, characters)
        simpleGrid.adapter = characterGridAdapter
        simpleGrid.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val intent = Intent(activity, CharacterFullActivity::class.java)
            intent.putExtra("CHARACTER", characters!![position])
            startActivity(intent)
        }
        simpleGrid.onItemLongClickListener = OnItemLongClickListener { parent, view, position, id ->
            sharedPreferencesFavorites!!.addFavorite(requireActivity(), characters!![position])
            //TODO Check if characters is already in favorites
            Toast.makeText(activity, "Added to favorites", Toast.LENGTH_LONG).show()
            true
        }
        val pullToRefresh: SwipeRefreshLayout = root.findViewById(R.id.fragment_characters_pullToRefresh)
        pullToRefresh.setOnRefreshListener {
            loadCharacters()
            pullToRefresh.isRefreshing = false
        }
        loadCharacters()
        return root
    }

    private fun loadCharacters() {
        if (getConnectivityStatus(requireActivity())) {
            loadOnline()
        } else {
            loadOffline()
        }
    }

    private fun loadOffline() {
        Toast.makeText(activity, R.string.networkError, Toast.LENGTH_SHORT).show()
        characters = sharedPreferencesCharacters!!.getcharacters(requireActivity())
        characterGridAdapter!!.notifyDataSetChanged()
    }

    private fun loadOnline() {
        characters!!.clear()
        val ids = ArrayList<Int>(5)
        val r = Random()
        var randomNumber: Int
        for (i in 0..7) {
            randomNumber = r.nextInt(NBCHARACTERS) + 1
            if (!ids.contains(randomNumber)) {
                ids.add(randomNumber)
                AsyncDownloadCharacter().execute(randomNumber, characters, characterGridAdapter)
            }
        }
        sharedPreferencesCharacters!!.savecharacters(requireActivity(), characters)
    }

    companion object {
        private const val NBCHARACTERS = 493
        var instance: CharacterFragment? = null
            get() {
                if (field == null) field = CharacterFragment()
                return field
            }
            private set
    }
}