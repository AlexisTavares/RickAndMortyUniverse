package alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.firebase

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

object AuthManager {
    val auth = FirebaseAuth.getInstance()
    var userGoogleAccount: GoogleSignInAccount? = null
    var googleSignInClient : GoogleSignInClient? = null

    fun signOut() {
        auth.signOut()
        googleSignInClient?.signOut()
    }
}