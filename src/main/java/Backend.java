import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Backend{
    private Map<String, Double> begin;
    private int numberOfStops;
    private ArrayList<Bar> bars;

    public Backend(String begin, int numberOfStops){
        verifyPostcode(begin);
        setParams(latLongFromPostcode(begin), numberOfStops);//I setparams as a separate method so I can try again without making a new obejct if need be
    }

    public Backend(Map begin, int numberOfStops){
        setParams(begin, numberOfStops);
    }

    Map<String, Double> latLongFromPostcode(String postcode){
        Map<String, Double> latLong = new HashMap<String, Double>();
        try {
            String url = ("https://api.postcodes.io/postcodes/"+postcode);
            JsonReader reader = new JsonReader();
            JSONObject postcodeInfo = reader.readJsonFromUrl(url).getJSONObject("result");
//            System.out.println(postcodeInfo.toString());
//            System.out.println(postcodeInfo.get("latitude"));

            latLong.put("longitude", (Double) postcodeInfo.get("longitude"));
            latLong.put("latitude", (Double) postcodeInfo.get("latitude"));


            return latLong;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    protected boolean verifyPostcode(String postcode){
        try {
            JsonReader reader = new JsonReader();
            JSONObject result = reader.readJsonFromUrl("https://api.postcodes.io/postcodes/" + postcode + "/validate");
//            System.out.println(result.toString());
//            System.out.println(result.get("result"));
            if (result.get("result").equals(true)){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


        /**
         * Commented out my own half done version to try out this pre-built jsonParser to save some time
         */
//        try {
//            String urlString = "https://api.postcodes.io/postcodes/"+postcode+"/validate";
////            System.out.println(urlString);
//            URL url = new URL(urlString);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
//
//            String info = reader.readLine();
//            System.out.println(info);
//            System.out.println();
//            return true;
//        }catch(Exception e){
//            e.printStackTrace();
//            return false;
//        }
    }

    public ArrayList<Bar> getBars() {
        return bars;
    }


    public URL getRoute() {
        return null;
    }


    public void setParams(Map<String, Double> begin, int numberOfStops) {
        this.begin=begin;
        this.numberOfStops=numberOfStops;
    }

    public boolean getGooglesInfo(Double lat, Double lon, int numberOfStops){
        String params = "location="+lat.toString()+","+ lon.toString()+"&rankby=distance&type=bar&";

        try {
//            URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + params + "key=AIzaSyDXKLWHJQdqzVI1agSREbzr4AuoBKyUeuE");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
//            String info = reader.readLine();


            String url = ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + params + "key=AIzaSyDXKLWHJQdqzVI1agSREbzr4AuoBKyUeuE");
            JsonReader reader = new JsonReader();
            JSONArray arrayOfBars = reader.readJsonFromUrl(url).getJSONArray("results");
//            System.out.println(arrayOfBars.toString());
            int length = arrayOfBars.length();
            int minOfOptions = Math.min(length, numberOfStops);
            //Looping through each of the bars or the total number of stops whichever is fewer
            bars = new ArrayList<Bar>();
            for(int i=0; i<minOfOptions; i++ ){
                JSONObject barInfo = arrayOfBars.getJSONObject(i);
//                System.out.println(barInfo.getJSONObject("geometry").getJSONObject("viewport"));
                Bar bar = (makeBar(barInfo));
                bar.setDescription(barInfo);
                bars.add(bar);
                System.out.println("Bar name: "+ bar.getName() + "\n Lat/long: "+ bar.getLatLong().toString()+"\n ID: "+ bar.getUniqueID()+"\n\n");
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    private Bar makeBar(JSONObject barInfo){
        JSONObject location = barInfo.getJSONObject("geometry").getJSONObject("location");
        return new Bar((String) barInfo.get("name"), (Double) location.get("lat"),(Double) location.get("lng"),(String) barInfo.get("place_id"));
    }

    //todo implement travelling salesman and give each bar a number
    public Map<Bar, Number> getBarsOrderMap() {
        //todo, go through each bar and build a distance matrix of all the places.
        //  get an array of the indices of each bar's position in the getBars() array in order.
        // Each bar now has an index in the bar list, map the bar to that index's index in the returned array
        return null;
    }
}
