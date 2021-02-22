package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.sharedpreferences.SharedPreferencesCollection
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.SplashScreenActivity
import alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters.CollectionAdapter
import alexis.tvrs.rickandmortyuniverse.wiki.ui.viewmodels.CharacterViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_collection.*

class CollectionFragment : Fragment() {
    private var mViewModel: CharacterViewModel? = null
    private var mAdapter: CollectionAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        mAdapter = CollectionAdapter { _, character ->
            mViewModel!!.selectedCharacter = character
            parentFragmentManager.beginTransaction()
                    .addToBackStack("CharacterDetailsFragment")
                    .setCustomAnimations(
                            R.anim.fragment_open_enter, R.anim.fragment_close_exit,
                            R.anim.fragment_open_enter, R.anim.fragment_close_exit
                    )
                    .replace(R.id.nav_host_fragment, CharacterDetailsFragment())
                    .commit()
        }
        collection_recyclerview.adapter = mAdapter
        fetchFavoritesCharacters()

        fab_add_collection.setOnClickListener{
            mViewModel!!.fetchCharacter(5).value?.let { it1 -> SplashScreenActivity.COLLECTION.add(it1) }
            SharedPreferencesCollection.saveCollected(view.context, SplashScreenActivity.COLLECTION)
        }
    }

    private fun fetchFavoritesCharacters(){
        mAdapter?.setData(SplashScreenActivity.COLLECTION)
    }

}