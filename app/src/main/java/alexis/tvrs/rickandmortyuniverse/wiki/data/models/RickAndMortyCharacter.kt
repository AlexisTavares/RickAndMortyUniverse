package alexis.tvrs.rickandmortyuniverse.wiki.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "character")
data class RickAndMortyCharacter(
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "status") var status: String,
        @ColumnInfo(name = "species") var species: String,
        @ColumnInfo(name = "type") var type: String,
        @ColumnInfo(name = "gender") var gender: String,

        var origin: RickAndMortyLocation,
        var location: RickAndMortyLocation,
        @ColumnInfo(name = "image") var image: String,
        var episodes: List<RickAndMortyEpisode>,
        @ColumnInfo(name = "url") var url: String
): Parcelable