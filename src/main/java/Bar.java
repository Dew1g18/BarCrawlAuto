import java.util.HashMap;

public class Bar {

    private String name;
    private HashMap<String, Double> latLong;
    private String uniqueID;
    private String description;


    public Bar(String name, HashMap<String, Double> latlong, String uniqueID) {
        this.name = name;
        this.latLong = latlong;
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Double> getLatLong() {
        return latLong;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
