package alexis.tvrs.rickandmortyuniverse.ui.episodes;

public class Episode {
    int id;
    String name;
    String air_date;
    String episodeOrder;

    public Episode(int id, String name, String air_date, String episobeOrder) {
        this.id = id;
        this.name = name;
        this.air_date = air_date;
        this.episodeOrder = episobeOrder;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAir_date() {
        return air_date;
    }

    public String getEpisodeOrder() {
        return episodeOrder;
    }
}
