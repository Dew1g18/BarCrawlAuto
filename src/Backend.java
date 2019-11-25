import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Backend implements BackendInterface {
    private HashMap<String, Double> begin;
    private int numberOfStops;

    public Backend(HashMap begin, int numberOfStops){
        setParams(begin, numberOfStops);

    }

    @Override
    public ArrayList<Bar> getBars() {
        return null;
    }

    @Override
    public URL getRoute() {
        return null;
    }

    @Override
    public void setParams(HashMap begin, int numberOfStops) {
        this.begin=begin;
        this.numberOfStops=numberOfStops;
    }

    private void getGooglesInfo(Double lat, Double lon, int numberOfStops){
        String params = "rankby=distance&type=bar&fields=formatted_address&";
        try {

            String location = "location="+Double.toString(lat)+","+Double.toString(lon)+"&";

            URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + params + location + "key=AIzaSyDXKLWHJQdqzVI1agSREbzr4AuoBKyUeuE");

        }catch(MalformedURLException e){
            e.printStackTrace();
        }
    }

    private Bar makeBar(){
        return null;
    }

    @Override
    public HashMap<Bar, Number> getBarsOrderMap() {
        return null;
    }
}
