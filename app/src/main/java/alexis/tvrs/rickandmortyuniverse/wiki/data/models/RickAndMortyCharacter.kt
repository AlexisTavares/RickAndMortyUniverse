package alexis.tvrs.rickandmortyuniverse.wiki.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
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
        var episodes: List<RickAndMortyEpisode>,
        var url: String
): Parcelable