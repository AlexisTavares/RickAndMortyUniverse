package alexis.tvrs.rickandmortyuniverse.wiki.activities

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.models.RickAndMortyCharacter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_character.*

class CharacterFullActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        val character = intent.getSerializableExtra("CHARACTER") as RickAndMortyCharacter
        Glide.with(this)
                .load(character.image)
                .into(characterLayout_image)

        val nameText = "Name: " + character.name
        characterLayout_name.text = nameText

        val statusText = "Status: " + character.status
        characterLayout_status.text = statusText

        val genderText = "Gender: " + character.gender
        characterLayout_genre.text = genderText

        val speciesText = "Species: " + character.species
        characterLayout_species.text = speciesText

        val originText = "Origin: " + (character.origin?.name ?:"Unknown")
        characterLayout_origin.text = originText
        characterLayout_origin.setOnClickListener { Toast.makeText(this, character.origin.toString(), Toast.LENGTH_LONG).show() }

        val lastLocationText = "Last Location: " + (character.location?.name ?:"Unknown")
        characterLayout_lastLocation.text = lastLocationText
        characterLayout_origin.setOnClickListener { Toast.makeText(this, character.location.toString(), Toast.LENGTH_LONG).show() }

    }
}