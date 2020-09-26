package alexis.tvrs.rickandmortyuniverse.ui.characters

import alexis.tvrs.rickandmortyuniverse.R
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class CharacterFullActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_full_layout)
        val character = intent.getSerializableExtra("CHARACTER") as Character
        val imageView = findViewById<ImageView>(R.id.characterLayout_image)
        Picasso.get().load(character.imageUri).into(imageView)
        val nameView = findViewById<TextView>(R.id.characterLayout_name)
        val nameText = "Name: " + character.name
        nameView.text = nameText
        val statusView = findViewById<TextView>(R.id.characterLayout_status)
        val statusText = "Status: " + character.status
        statusView.text = statusText
        val genderView = findViewById<TextView>(R.id.characterLayout_genre)
        val genderText = "Gender: " + character.gender
        genderView.text = genderText
        val speciesView = findViewById<TextView>(R.id.characterLayout_species)
        val speciesText = "Species: " + character.species
        speciesView.text = speciesText
        val originView = findViewById<TextView>(R.id.characterLayout_origin)
        val originText = "Origin: " + character.origin
        originView.text = originText
        originView.setOnClickListener { Toast.makeText(this, character.originUri, Toast.LENGTH_LONG).show() }
        val locationView = findViewById<TextView>(R.id.characterLayout_lastLocation)
        val lastLocationText = "Last Location: " + character.lastLocation
        locationView.text = lastLocationText
    }
}