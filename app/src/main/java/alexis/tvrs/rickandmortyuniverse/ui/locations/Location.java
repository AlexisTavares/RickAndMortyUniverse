package alexis.tvrs.rickandmortyuniverse.ui.locations;

public class Location {
    int id;
    String name;
    String type;
    String dimension;

    public Location(int id, String name, String type, String dimension) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dimension = dimension;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDimension() {
        return dimension;
    }
}
