package alexis.tvrs.rickandmortyuniverse.wiki.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.sharedpreferences.SharedPreferencesFavorites
import alexis.tvrs.rickandmortyuniverse.wiki.activities.SplashScreenActivity
import alexis.tvrs.rickandmortyuniverse.wiki.adapters.CharacterAdapter
import alexis.tvrs.rickandmortyuniverse.wiki.activities.CharacterFullActivity
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

class FavoriteFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
//        val sharedPreferencesFavorites = SharedPreferencesFavorites()
        val simpleGrid = root.findViewById<GridView>(R.id.fragment_favorites_simpleGridView)
        val characterGridAdapter = CharacterAdapter(requireContext(), SplashScreenActivity.FAVORITES)
        simpleGrid.adapter = characterGridAdapter
        characterGridAdapter.notifyDataSetChanged()
        simpleGrid.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val intent = Intent(activity, CharacterFullActivity::class.java)
            intent.putExtra("CHARACTER", SplashScreenActivity.FAVORITES[position])
            startActivity(intent)
        }
        simpleGrid.onItemLongClickListener = OnItemLongClickListener { parent, view, position, id ->
            SplashScreenActivity.FAVORITES.remove(SplashScreenActivity.FAVORITES[position])
//            sharedPreferencesFavorites.saveFavorites(requireActivity(), SplashScreenActivity.FAVORITES)
            characterGridAdapter.notifyDataSetChanged()
            Toast.makeText(activity, "Removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }
        return root
    }
}