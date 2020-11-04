package alexis.tvrs.rickandmortyuniverse.wiki.data.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize

data class RickAndMortyCharacter(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: RickAndMortyLocation,
    var location: RickAndMortyLocation,
    var image: String,
    var episode: List<String>,
    var url: String,
): Parcelable