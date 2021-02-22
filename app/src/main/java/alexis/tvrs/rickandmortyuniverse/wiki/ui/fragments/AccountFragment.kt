package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.UserData
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.firebase.AuthManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
                .load(AuthManager.userGoogleAccount?.photoUrl)
                .centerCrop()
                .into(imgUser)
        account_name.text = AuthManager.userGoogleAccount?.displayName
        account_balance.text = UserData.balance.toString()
        balance_title.text = getString(R.string.account_balance)
    }
}