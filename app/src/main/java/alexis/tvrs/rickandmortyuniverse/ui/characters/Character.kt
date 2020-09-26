package alexis.tvrs.rickandmortyuniverse.ui.characters

import java.io.Serializable

class Character(val characterId: Int, val imageUri: String, val name: String, val status: String, val species: String, val gender: String, val origin: String, val lastLocation: String) : Serializable {
    var originUri: String? = null
        private set
    var lastLocationUri: String? = null
        private set

    fun SetOriginUri(originUri: String?) {
        this.originUri = originUri
    }

    fun SetLastLocationUri(lastLocationUri: String?) {
        this.lastLocationUri = lastLocationUri
    }
}