package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.MainActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_character_details.view.*
import kotlinx.android.synthetic.main.view_holder_character.view.*

class CharacterDetailsFragment: Fragment() {
    private lateinit var characterToDisplay: RickAndMortyCharacter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            characterToDisplay = arguments?.getParcelable(ARG_CHAR)!!
        } catch (e: IllegalStateException) {
            (activity as? MainActivity)?.onBackPressed()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(view)
                .load(characterToDisplay.image)
                .centerCrop()
                .into(view.characterLayout_image)
        val nameText = "Name: " + characterToDisplay.name
        view.characterLayout_name.text = nameText
        val statusText = "Status: " + characterToDisplay.status
        view.characterLayout_status.text = statusText
        val genderText = "Gender: " + characterToDisplay.gender
        view.characterLayout_genre.text = genderText
        val speciesText = "Species: " + characterToDisplay.species
        view.characterLayout_species.text = speciesText
        val originText = "Origin: " + characterToDisplay.origin.name
        view.characterLayout_origin.text = originText
    }
    companion object{
        const val ARG_CHAR = "arg_character"

        fun newInstance(c: RickAndMortyCharacter): CharacterDetailsFragment{
            val args = Bundle()
            args.putParcelable(ARG_CHAR, c)
            val fragment = CharacterDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}