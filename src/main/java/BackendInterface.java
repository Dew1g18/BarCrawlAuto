import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface BackendInterface {

    void setParams(Map<String, Double> postcode, int numberOfStops );

    ArrayList<Bar> getBars();

    URL getRoute();

    Map<Bar, Number> getBarsOrderMap();

}
