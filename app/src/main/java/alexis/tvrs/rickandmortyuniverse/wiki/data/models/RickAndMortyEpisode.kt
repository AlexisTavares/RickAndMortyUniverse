package alexis.tvrs.rickandmortyuniverse.wiki.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "episode")
data class RickAndMortyEpisode(
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "airDate") var airDate: String,
        @ColumnInfo(name = "episode") var episode: String,
        var characters: List<String>,
        @ColumnInfo(name = "url") var url: String,
        @ColumnInfo(name = "created") var created: String,
): Parcelable