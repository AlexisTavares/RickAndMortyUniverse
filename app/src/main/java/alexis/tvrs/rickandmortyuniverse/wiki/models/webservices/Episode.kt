package alexis.tvrs.rickandmortyuniverse.wiki.models.webservices

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Episode : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("air_date")
    @Expose
    var airDate: String? = null

    @SerializedName("episode")
    @Expose
    var episode: String? = null

    @SerializedName("characters")
    @Expose
    var characters: List<String>? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("created")
    @Expose
    var created: String? = null

    companion object {
        private const val serialVersionUID = -5667862611817692642L
    }
}