package alexis.tvrs.rickandmortyuniverse.wiki.ui.viewmodels

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.MarketplaceOffer
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.UserData
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.firestore.FirestoreRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import java.util.*

class MarketplaceViewModel : ViewModel(){
    private val TAG = "MARKETPLACE_VIEW_MODEL"
    var marketplaceItems : MutableLiveData<List<MarketplaceOffer>> = MutableLiveData()

    fun createMarketplaceOfferAndSaveToFirebase(character : RickAndMortyCharacter, price : Int){
        FirestoreRepository.saveMarketplaceOffer(character, price)
    }

    fun getMarketplaceOffers(): LiveData<List<MarketplaceOffer>>{
        FirestoreRepository.getSavedMarketplaceOffers().addSnapshotListener(EventListener { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                marketplaceItems.value = null
                return@EventListener
            }
            val savedMarketplaceItems : MutableList<MarketplaceOffer> = mutableListOf()
            for (doc in value!!) {
                savedMarketplaceItems.add(doc.toObject(MarketplaceOffer::class.java))
            }
            marketplaceItems.value = savedMarketplaceItems
        })
        return marketplaceItems
    }

    fun buyMarketplaceOffer(marketplaceOffer: MarketplaceOffer){
        if(UserData.balance > marketplaceOffer.price) {
            UserData.balance -= marketplaceOffer.price
            FirestoreRepository.updateUserData(UserData)
            FirestoreRepository.deleteMarketplaceOffer(marketplaceOffer).addOnFailureListener {
                Log.e(TAG,"Failed to buy MarketplaceItem")
            }
        }
    }

    fun deleteMarketplaceOffer(marketplaceItem: MarketplaceOffer){
        FirestoreRepository.deleteMarketplaceOffer(marketplaceItem).addOnFailureListener {
            Log.e(TAG,"Failed to delete MarketplaceItem")
        }
    }

}