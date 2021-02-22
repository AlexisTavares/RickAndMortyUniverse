package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.utils.ScreenUtils
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.MainActivity
import alexis.tvrs.rickandmortyuniverse.wiki.ui.viewmodels.CharacterViewModel
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_character_details.view.*

class CharacterDetailsFragment: Fragment() {
    private var mViewModel: CharacterViewModel? = null
    private lateinit var characterToDisplay: RickAndMortyCharacter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mViewModel = ViewModelProvider(requireActivity()).get(CharacterViewModel::class.java)
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

        characterToDisplay = mViewModel!!.selectedCharacter!!

        Glide.with(view)
                .load(characterToDisplay.image)
                .override( ScreenUtils.getScreenWidth(view.context))
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
}