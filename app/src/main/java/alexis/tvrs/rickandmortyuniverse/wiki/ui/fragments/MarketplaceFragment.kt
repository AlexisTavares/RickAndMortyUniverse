package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.sharedpreferences.SharedPreferencesFavorites
import alexis.tvrs.rickandmortyuniverse.utils.MockUtils
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.MarketplaceOffer
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.SplashScreenActivity
import alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters.CharacterAdapter
import alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters.MarketplaceItemAdapter
import alexis.tvrs.rickandmortyuniverse.wiki.ui.viewmodels.MarketplaceViewModel
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_characters.*

class MarketplaceFragment : Fragment() {
    private lateinit var mAdapter: MarketplaceItemAdapter
    private lateinit var mViewModel: MarketplaceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_marketplace, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MarketplaceViewModel::class.java)
        mAdapter = MarketplaceItemAdapter { _, marketplaceOffer ->
            parentFragmentManager.beginTransaction()
                    mViewModel.buyMarketplaceOffer(marketplaceOffer)
        }
        character_recyclerview.adapter = mAdapter
        fetchOffers()
    }

    private fun fetchOffers(){
//        mViewModel.getMarketplaceOffers().observe(viewLifecycleOwner, {
//            mAdapter.setData(it)
//        })
        mAdapter.setData(MockUtils.marketplaceOffers)
    }

}