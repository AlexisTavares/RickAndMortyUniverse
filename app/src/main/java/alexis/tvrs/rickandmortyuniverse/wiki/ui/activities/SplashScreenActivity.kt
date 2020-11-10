package alexis.tvrs.rickandmortyuniverse.wiki.ui.activities

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.sharedpreferences.SharedPreferencesFavorites
import alexis.tvrs.rickandmortyuniverse.utils.ScreenUtils
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import android.app.Activity
import android.content.Intent
import android.os.Bundle

class SplashScreenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val favorites = SharedPreferencesFavorites.getFavorites(this)

        CARD_SIZE = ScreenUtils.getScreenWidth(this)/2

        if (favorites!= null)
            FAVORITES.addAll(favorites)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }



    companion object {
        var CARD_SIZE = 50
        var FAVORITES = ArrayList<RickAndMortyCharacter>()
    }


}