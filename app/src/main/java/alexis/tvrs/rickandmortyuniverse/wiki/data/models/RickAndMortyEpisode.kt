package alexis.tvrs.rickandmortyuniverse.wiki.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class RickAndMortyEpisode(
    var id: Int,
    var name: String,
    var airDate: String,
    var episode: String,
    var characters: List<String>,
    var url: String,
    var created: String,
): Parcelable