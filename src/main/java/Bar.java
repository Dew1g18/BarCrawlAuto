import java.util.HashMap;

public class Bar {

    private String name;
    private HashMap<String, Double> latLong;
    private String uniqueID;
    private String description;


    public Bar(String name, Double latitude, Double longitude, String uniqueID) {
        this.name = name;
        this.latLong=new HashMap<String, Double>();
        this.latLong.put("latitude", latitude);
        this.latLong.put("longitude", longitude);
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
