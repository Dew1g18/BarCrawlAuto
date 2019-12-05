import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TravellingSalesman {

    //Todo implement travelling salesman, probably from google.ortools, not sure how to use maven with it though
    //will work it out tomorrow
    public TravellingSalesman(){

    }

    public double distanceBetween(HashMap<String, Double> orig, ArrayList<Bar> bars){
        //todo, use the google api distance matrix to get individual distances for bars and start point.
        String origin = "origins="+orig.get("latitude").toString()+","+orig.get("longitude")+"&";

        JsonReader reader = new JsonReader();
        try {
            JSONObject distJson = reader.readJsonFromUrl("https://maps.googleapis.com/maps/api/distancematrix/json?" +origin+  "&key=AIzaSyDXKLWHJQdqzVI1agSREbzr4AuoBKyUeuE");
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0.0;
    }

//    public double

    //todo use google OR tools to find a tsp directed graph of all the points




}
