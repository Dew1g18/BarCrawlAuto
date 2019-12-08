import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TravellingSalesman {

    //Todo implement travelling salesman, probably from google.ortools, not sure how to use maven with it though
    //will work it out tomorrow
    public TravellingSalesman(){

    }

    public double[] distanceBetween(HashMap<String, Double> orig, ArrayList<Bar> bars){
        //todo, use the google api distance matrix to get individual distances for bars and start point.
        String origin = "origins="+orig.get("latitude").toString()+","+orig.get("longitude")+"&";
        String destinations = "destinations=";
        String extraParams = "&mode=walking";

        for(Bar bar: bars){
            destinations+="place_id="+bar.getUniqueID()+"%";
        }
        JsonReader reader = new JsonReader();
        try {
            JSONObject distJson = reader.readJsonFromUrl("https://maps.googleapis.com/maps/api/distancematrix/json?" +origin+  "&key=AIzaSyDXKLWHJQdqzVI1agSREbzr4AuoBKyUeuE");
            //todo, turn the returned JSON into a section of the distance matrix.
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    public double

    //todo use google OR tools to find a tsp directed graph of all the points




}
