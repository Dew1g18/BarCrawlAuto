import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public interface BackendInterface {

    void setParams(HashMap<String, Double> startLatLong, int numberOfStops );

    ArrayList<Bar> getBars();

    URL getRoute();

    HashMap<Bar, Number> getBarsOrderMap();

}
