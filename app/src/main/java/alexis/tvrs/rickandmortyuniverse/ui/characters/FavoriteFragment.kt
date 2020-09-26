package alexis.tvrs.rickandmortyuniverse.ui.characters

import alexis.tvrs.rickandmortyuniverse.R
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
import java.util.*
import kotlin.collections.ArrayList

class FavoriteFragment : Fragment() {
    private var favoriteCharacters: ArrayList<Character?>? = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        val sharedPreferencesFavorites = SharedPreferencesFavorites()
        favoriteCharacters = sharedPreferencesFavorites.getFavorites(requireContext())
        val simpleGrid = root.findViewById<GridView>(R.id.fragment_favorites_simpleGridView)
        val characterGridAdapter = CharacterGridAdapter(this.context, favoriteCharacters)
        simpleGrid.adapter = characterGridAdapter
        characterGridAdapter.notifyDataSetChanged()
        simpleGrid.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val intent = Intent(activity, CharacterFullActivity::class.java)
            intent.putExtra("CHARACTER", favoriteCharacters!![position])
            startActivity(intent)
        }
        simpleGrid.onItemLongClickListener = OnItemLongClickListener { parent, view, position, id ->
            favoriteCharacters!!.remove(favoriteCharacters!![position])
            sharedPreferencesFavorites.saveFavorites(requireActivity(), favoriteCharacters)
            characterGridAdapter.notifyDataSetChanged()
            Toast.makeText(activity, "Removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }
        return root
    }
}