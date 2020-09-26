package alexis.tvrs.rickandmortyuniverse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
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
    private lateinit var callbackManager: CallbackManager
    private lateinit var mGoogleSignInClient:GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    continueFacebook()
                }
                override fun onCancel() {}
                override fun onError(exception: FacebookException) {
                    Log.w("FACEBOOK SIGN-IN ERROR", "signInResult:failed code=" + exception.message)
                }
            })



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

        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        val googleAccount = GoogleSignIn.getLastSignedInAccount(this)
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if (googleAccount != null) {
            continueGoogle(googleAccount)
        }
        else if(isLoggedIn){
            continueFacebook()
        }
    }

    private fun continueFacebook() {
        val intent = Intent(this, MainActivity::class.java)
        val facebookAccount = Profile.getCurrentProfile()
        intent.putExtra("ACCOUNT_TYPE","FACEBOOK")
        intent.putExtra("USER_ACCOUNT", facebookAccount)
            startActivity(intent)
    }

    private fun continueGoogle(googleAccount: GoogleSignInAccount) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("ACCOUNT_TYPE","GOOGLE")
        intent.putExtra("USER_ACCOUNT", googleAccount)
        startActivity(intent)
    }

    private fun signInGoogle(){
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
            continueGoogle(account)
        } catch (e: ApiException) {
            Log.w("GOOGLE SIGN-IN ERROR", "signInResult:failed code=" + e.statusCode)
        }

    }


}