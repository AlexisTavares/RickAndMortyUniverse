package alexis.tvrs.rickandmortyuniverse.wiki.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "location")
data class RickAndMortyLocation(
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "type") var type: String,
        @ColumnInfo(name = "dimension") var dimension: String,
        var residents: List<RickAndMortyCharacter>,
        @ColumnInfo(name = "url") var url: String,
        @ColumnInfo(name = "created") var created: String,
): Parcelable
