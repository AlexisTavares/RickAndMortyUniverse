package alexis.tvrs.rickandmortyuniverse.wiki.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class RickAndMortyLocation(
    var id: Int,
    var name: String,
    var type: String,
    var dimension: String,
    var residents: List<String>,
    var url: String,
    var created: String,
): Parcelable