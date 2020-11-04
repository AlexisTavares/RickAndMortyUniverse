package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters.CharacterAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_characters.*

class CharacterFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        character_recyclerview.adapter = CharacterAdapter { _, c ->
            parentFragmentManager.beginTransaction()
                    .addToBackStack("CharacterDetailsFragment")
                    .setCustomAnimations(
                            R.anim.fragment_open_enter, R.anim.fragment_close_exit,
                            R.anim.fragment_open_enter, R.anim.fragment_close_exit
                    )
                    .replace(R.id.nav_host_fragment, CharacterDetailsFragment.newInstance(c))
                    .commit()
        }

//        gridView.onItemLongClickListener = OnItemLongClickListener { parent, view, position, id ->
//            if(SplashScreenActivity.FAVORITES.contains(SplashScreenActivity.CHARACTERS[position])){
//                Toast.makeText(activity,"Character already saved as favorite", Toast.LENGTH_SHORT).show()
//            }else {
//                SplashScreenActivity.FAVORITES.add(SplashScreenActivity.CHARACTERS[position])
//                SharedPreferencesFavorites.saveFavorites(view.context, SplashScreenActivity.FAVORITES)
//                Toast.makeText(activity, "Added to favorites", Toast.LENGTH_SHORT).show()
//            }
//            true
//        }
    }
}