package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.sharedpreferences.SharedPreferencesFavorites
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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_characters.*


class CharacterFragment : Fragment() {
    private lateinit var  mAdapter: CharacterAdapter
    private lateinit var mViewModel: CharacterViewModel
    private var isCharacterLoadNeeded = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(requireActivity()).get(CharacterViewModel::class.java)
        mAdapter = CharacterAdapter(
                //OnClick
                { _, character ->
                    mViewModel.selectedCharacter = character
                    parentFragmentManager.beginTransaction()
                            .addToBackStack("CharacterDetailsFragment")
                            .setCustomAnimations(
                                    R.anim.fragment_open_enter, R.anim.fragment_close_exit,
                                    R.anim.fragment_open_enter, R.anim.fragment_close_exit
                            )
                            .replace(R.id.nav_host_fragment, CharacterDetailsFragment())
                            .commit()
                },
                //OnLongClick
                { _, c ->
                    if (SplashScreenActivity.FAVORITES.contains(c)) {
                        Toast.makeText(activity, "Character already saved as favorite", Toast.LENGTH_SHORT).show()
                    } else {
                        SplashScreenActivity.FAVORITES.add(c)
                        SharedPreferencesFavorites.saveFavorites(view.context, SplashScreenActivity.FAVORITES)
                        Toast.makeText(activity, "Added to favorites", Toast.LENGTH_SHORT).show()
                    }
                    true
                }

        )
        character_recyclerview.adapter = mAdapter
        character_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val visibleThreshold = 2
                    val layoutManager = character_recyclerview.layoutManager as StaggeredGridLayoutManager
                    val lastItem = layoutManager.findLastCompletelyVisibleItemPositions(null)
                    val currentTotalCount = layoutManager.itemCount
                    for(item in lastItem) {
                        if (currentTotalCount <= item + visibleThreshold){
                            loadMoreCharacters()
                            isCharacterLoadNeeded = true
                            break
                        }
                    }
                }
        })
        fetchCharacters()
    }

    override fun onResume() {
        super.onResume()
        mViewModel.selectedPage = 1

    }

    private fun loadMoreCharacters(){
        if(isCharacterLoadNeeded) {
            mViewModel.selectedPage++
            mViewModel.fetchCharacters()
        }
    }

    private fun fetchCharacters(){
        mViewModel.rickAndMortyCharacters.observe(viewLifecycleOwner) {
            mAdapter.setData(it)
        }
        mViewModel.fetchCharacters()
    }
}