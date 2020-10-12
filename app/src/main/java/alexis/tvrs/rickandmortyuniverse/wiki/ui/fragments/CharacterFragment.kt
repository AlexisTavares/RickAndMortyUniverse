package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.sharedpreferences.SharedPreferencesFavorites
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.SplashScreenActivity
import alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters.CharacterAdapter
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.CharacterFullActivity
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

class CharacterFragment : Fragment() {
    private var characterAdapter: CharacterAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_characters, container, false)
        characterAdapter =  CharacterAdapter(root.context, SplashScreenActivity.CHARACTERS)

        val gridView = root.findViewById<GridView>(R.id.fragment_characters_simpleGridView)
        gridView.adapter = characterAdapter

        gridView.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val intent = Intent(activity, CharacterFullActivity::class.java)
            intent.putExtra("CHARACTER", SplashScreenActivity.CHARACTERS[position])
            startActivity(intent)
        }

        gridView.onItemLongClickListener = OnItemLongClickListener { parent, view, position, id ->
            SplashScreenActivity.FAVORITES.add(SplashScreenActivity.CHARACTERS[position])
            SharedPreferencesFavorites.saveFavorites(root.context, SplashScreenActivity.FAVORITES)
            Toast.makeText(activity, "Added to favorites", Toast.LENGTH_LONG).show()
            true
        }
        return root
    }
}