package alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.firestore

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.MarketplaceOffer
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.UserData
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object FirestoreRepository {
    var user = FirebaseAuth.getInstance().currentUser
    var db = FirebaseFirestore.getInstance()

    //Marketplace
    fun saveMarketplaceOffer(character: RickAndMortyCharacter, price : Int){
        val offer = HashMap<String, Any>()
        offer["character_id"] = character.id
        offer["character_image"] = character.image
        offer["price"] = price
        offer["seller_id"] = user!!.uid
        db.collection("offers")
            .add(offer)
            .addOnSuccessListener {
                Log.d("Offer Creation OK", "OfferSnapshot added with ID: ${it.id}");
            }
            .addOnFailureListener() {
                Log.e("Offer Creation NOK", it.message.toString());
            }
    }

    fun getSavedMarketplaceOffers(): CollectionReference {
        return db.collection("offers")
    }

    fun deleteMarketplaceOffer(marketplaceItem: MarketplaceOffer): Task<Void> {
        val documentReference =  db.collection("offers")
                .document(marketplaceItem.id)
        return documentReference.delete()
    }

    //UserData
    fun fetchUserData() : CollectionReference{
        return db.collection("users/${user!!.uid}/data")
    }

    fun updateUserData(userData : UserData) {
        val userDataMap = HashMap<String, Any>()
        userDataMap["balance"] = userData.balance
        db.collection("users").document("${user!!.uid}/data").update(userDataMap)
    }

    fun deleteUserData(): Task<Void> {
        val documentReference =  db.collection("users/${user!!.uid}/data")
                .document()
        return documentReference.delete()
    }
}