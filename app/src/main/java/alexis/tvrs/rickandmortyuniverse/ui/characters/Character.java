package alexis.tvrs.rickandmortyuniverse.ui.characters;

import java.io.Serializable;

public class Character implements Serializable {
    private int characterId;
    private String imageUri;
    private String name;
    private String status;
    private String species;
    private String gender;
    private String origin;
    private String originUri;
    private String lastLocation;
    private String lastLocationUri;

    public Character(int charactedId, String imageUri, String name, String status, String species, String gender, String origin, String lastLocation) {
        this.characterId = charactedId;
        this.imageUri = imageUri;
        this.name = name;
        this.status = status;
        this.species = species;
        this.gender = gender;
        this.origin = origin;
        this.lastLocation = lastLocation;
    }

    public int getCharacterId() { return characterId; }

    public String getImageUri() {
        return imageUri;
    }

    public String getName() {
        return name;
    }

    public String getStatus() { return status; }

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public String getOriginUri() { return originUri; }

    public String getLastLocationUri() { return lastLocationUri; }

    public void SetOriginUri (String originUri) { this.originUri = originUri; }

    public void SetLastLocationUri (String lastLocationUri) { this.lastLocationUri = lastLocationUri; }

}

