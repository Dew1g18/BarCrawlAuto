import java.util.HashMap;

public class Bar {
    String name;
    HashMap<String, Double> latLong;
    String uniqueID;

    public Bar(String name, HashMap<String, Double> latlong, String uniqueID) {
        this.name = name;
        this.latLong = latlong;
        this.uniqueID = uniqueID;
    }
}
