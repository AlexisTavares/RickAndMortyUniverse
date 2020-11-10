package alexis.tvrs.rickandmortyuniverse.wiki.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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