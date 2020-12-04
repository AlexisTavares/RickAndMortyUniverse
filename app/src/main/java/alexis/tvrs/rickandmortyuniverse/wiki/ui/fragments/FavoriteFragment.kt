package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.sharedpreferences.SharedPreferencesFavorites
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.RickAndMortyDatasource
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.SplashScreenActivity
import alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters.CharacterAdapter
import alexis.tvrs.rickandmortyuniverse.wiki.ui.viewmodels.CharacterViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_characters.*

class FavoriteFragment : Fragment() {
    private var mViewModel: CharacterViewModel? = null
    private var mAdapter: CharacterAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        mAdapter = CharacterAdapter(
                { _, characterPosition ->
                    mViewModel!!.selectedCharacter = characterPosition
                    parentFragmentManager.beginTransaction()
                            .addToBackStack("CharacterDetailsFragment")
                            .setCustomAnimations(
                                    R.anim.fragment_open_enter, R.anim.fragment_close_exit,
                                    R.anim.fragment_open_enter, R.anim.fragment_close_exit
                            )
                            .replace(R.id.nav_host_fragment, CharacterDetailsFragment())
                            .commit()
                },
                {_, c ->
                    if(SplashScreenActivity.FAVORITES.contains(c)){
                        SplashScreenActivity.FAVORITES.remove(c)
                        Toast.makeText(activity,"Removed from favorites", Toast.LENGTH_SHORT).show()
                        SharedPreferencesFavorites.saveFavorites(view.context, SplashScreenActivity.FAVORITES)
                        mAdapter?.notifyDataSetChanged()
                    }
                    true
                }
        )
        character_recyclerview.adapter = mAdapter
        fetchFavoritesCharacters()
    }

    private fun fetchFavoritesCharacters(){
        mAdapter?.setData(SplashScreenActivity.FAVORITES)
    }

}