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
//        Map<String, Double> latLongBegin = latLongFromPostcode(begin);
//TODO Change string postcode to lat long
//        setParams(begin, numberOfStops);

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
            //todo finish forming the http request correctly
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
            //Looping through each of the bars or the total number of stops whichever is fewer
            bars = new ArrayList<Bar>();
            for(int i=0; i<length; i++ ){
                JSONObject barInfo = arrayOfBars.getJSONObject(i);
//                System.out.println(barInfo.getJSONObject("geometry").getJSONObject("viewport"));
                Bar bar = (makeBar(barInfo));
                bars.add(bar);
                System.out.println("Bar name: "+ bar.getName() + "\n Lat/long: "+ bar.getLatLong().toString()+"\n ID: "+ bar.getUniqueID()+"\n\n");
            }


            //todo by this point reading the json should be trivial so use that to read bar information and create bars.

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //todo make the makebar method to make a bar from a json object
    private Bar makeBar(JSONObject barInfo){
        JSONObject location = barInfo.getJSONObject("geometry").getJSONObject("location");
        return new Bar((String) barInfo.get("name"), (Double) location.get("lat"),(Double) location.get("lng"),(String) barInfo.get("place_id"));
    }

    //todo implement travelling salesman and give each bar a number
    public Map<Bar, Number> getBarsOrderMap() {
        return null;
    }
}
