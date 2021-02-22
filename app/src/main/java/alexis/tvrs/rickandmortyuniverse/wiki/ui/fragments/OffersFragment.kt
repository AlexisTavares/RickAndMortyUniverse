package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.utils.MockUtils
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.MarketplaceOffer
import alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters.MarketplaceItemAdapter
import alexis.tvrs.rickandmortyuniverse.wiki.ui.viewmodels.MarketplaceViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_offers.*

class OffersFragment : Fragment() {
    private lateinit var mAdapter: MarketplaceItemAdapter
    private lateinit var mViewModel: MarketplaceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_offers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MarketplaceViewModel::class.java)
        mAdapter = MarketplaceItemAdapter { _, marketplaceOffer ->
            parentFragmentManager.beginTransaction()
            mViewModel.buyMarketplaceOffer(marketplaceOffer)
        }
        offers_recyclerview.adapter = mAdapter
        fetchOffers()

        fab_add_offer.setOnClickListener{
            mViewModel.createMarketplaceOfferAndSaveToFirebase(MockUtils.dummyCharacter,25)
        }
    }

    private fun fetchOffers(){
//        mViewModel.getMarketplaceOffers().observe(viewLifecycleOwner, {
//            mAdapter.setData(it)
//        })
        mAdapter.setData(MockUtils.marketplaceOffers)
    }

}