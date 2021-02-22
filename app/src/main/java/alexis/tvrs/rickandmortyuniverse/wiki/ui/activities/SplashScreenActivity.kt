package alexis.tvrs.rickandmortyuniverse.wiki.ui.activities

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.sharedpreferences.SharedPreferencesCollection
import alexis.tvrs.rickandmortyuniverse.sharedpreferences.SharedPreferencesFavorites
import alexis.tvrs.rickandmortyuniverse.utils.ScreenUtils
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.firebase.AuthManager
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


class SplashScreenActivity : Activity() {
    val TAG = "SPLASH_ACTIVITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val favorites = SharedPreferencesFavorites.getFavorites(this)
        CARD_SIZE = ScreenUtils.getScreenWidth(this)/2
        if (favorites != null) FAVORITES.addAll(favorites)

        val collection = SharedPreferencesCollection.getCollected(this)
        CARD_SIZE = ScreenUtils.getScreenWidth(this)/2
        if (collection != null) COLLECTION.addAll(collection)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = AuthManager.auth.currentUser

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        AuthManager.googleSignInClient = GoogleSignIn.getClient(this, gso)
        loginUser(currentUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                AuthManager.userGoogleAccount = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(AuthManager.userGoogleAccount!!.idToken!!)
            } catch (e: ApiException) {
                e.message?.let { Log.d(TAG, it) }
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        AuthManager.auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithCredential:success")
                        val user = AuthManager.auth.currentUser
                        loginUser(user)
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        loginUser(null)
                    }
                }
    }

    private fun signIn() {
        val signInIntent = AuthManager.googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun loginUser(user: FirebaseUser?) {
        if (user != null) {
            AuthManager.userGoogleAccount = GoogleSignIn.getLastSignedInAccount(this)
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            signIn()
        }
    }

    companion object {
        const val RC_SIGN_IN = 1010

        var CARD_SIZE = 50

        var FAVORITES = ArrayList<RickAndMortyCharacter>()

        var COLLECTION = ArrayList<RickAndMortyCharacter>()
    }
}