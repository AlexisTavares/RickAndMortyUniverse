package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.sharedpreferences.SharedPreferencesFavorites
import alexis.tvrs.rickandmortyuniverse.wiki.data.repositories.RickAndMortyRepository
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.SplashScreenActivity
import alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters.CharacterAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_characters.*

class CharacterFragment : Fragment() {
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
        mAdapter = CharacterAdapter(
                { _, c ->
                    parentFragmentManager.beginTransaction()
                            .addToBackStack("CharacterDetailsFragment")
                            .setCustomAnimations(
                                    R.anim.fragment_open_enter, R.anim.fragment_close_exit,
                                    R.anim.fragment_open_enter, R.anim.fragment_close_exit
                            )
                            .replace(R.id.nav_host_fragment, CharacterDetailsFragment.newInstance(c))
                            .commit()
                }
                ,{_, c ->
                    if(SplashScreenActivity.FAVORITES.contains(c)){
                        Toast.makeText(activity,"Character already saved as favorite", Toast.LENGTH_SHORT).show()
                    }else {
                        SplashScreenActivity.FAVORITES.add(c)
                        SharedPreferencesFavorites.saveFavorites(view.context, SplashScreenActivity.FAVORITES)
                        Toast.makeText(activity, "Added to favorites", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
        )
        character_recyclerview.adapter = mAdapter
        fetchCharacters()
    }

    private fun fetchCharacters(){
        RickAndMortyRepository.rickAndMortyCharactersLiveData.observe(viewLifecycleOwner, {
            listCharacters ->
            mAdapter?.setData(listCharacters)
        })
        RickAndMortyRepository.fetchRickAndMortyCharacters()
    }
}