package alexis.tvrs.rickandmortyuniverse.wiki.activities

import alexis.tvrs.rickandmortyuniverse.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity:AppCompatActivity() {
    private val RC_SIGN_IN = 12
    private lateinit var mGoogleSignInClient:GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        bt_signin_google.setSize(SignInButton.SIZE_STANDARD)
        bt_signin_google.setOnClickListener{signInGoogle()}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task:Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        val googleAccount = GoogleSignIn.getLastSignedInAccount(this)
        if (googleAccount != null) {
            continueGoogle(googleAccount)
        }
    }

    private fun continueGoogle(googleAccount: GoogleSignInAccount?){
        if (googleAccount != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("ACCOUNT_TYPE", "GOOGLE")
            intent.putExtra("USER_ACCOUNT", googleAccount)
            startActivity(intent)
        }
    }

    private fun signInGoogle(){
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
            continueGoogle(account)
        } catch (e: ApiException) {
            Log.w("GOOGLE SIGN-IN ERROR", "signInResult:failed code=" + e.statusCode)
        }

    }
}