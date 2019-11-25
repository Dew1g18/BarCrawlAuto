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

    private void getGooglesInfo(){

    }

    private Bar makeBar(){
        return null;
    }

    @Override
    public HashMap<Bar, Number> getBarsOrderMap() {
        return null;
    }
}
