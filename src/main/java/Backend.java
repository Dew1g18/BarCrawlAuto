import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Backend implements BackendInterface {
    private Map<String, Double> begin;
    private int numberOfStops;

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
            System.out.println(postcodeInfo.toString());
//            System.out.println(postcodeInfo.get("latitude"));

            latLong.put("longitude", (Double) postcodeInfo.get("longitude"));
            latLong.put("latitude", (Double) postcodeInfo.get("latitude"));


            //todo implement getting lat long from json

            return latLong;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //todo: Implement a postcode verification method.
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

    @Override
    public ArrayList<Bar> getBars() {
        return null;
    }

    @Override
    public URL getRoute() {
        return null;
    }

    @Override
    public void setParams(Map<String, Double> begin, int numberOfStops) {
        this.begin=begin;
        this.numberOfStops=numberOfStops;
    }

    private boolean getGooglesInfo(Double lat, Double lon, int numberOfStops){
        String params = "rankby=distance&type=bar&fields=formatted_address&";

        try {
            //todo finish forming the http request correctly
            URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + params + "key=AIzaSyDXKLWHJQdqzVI1agSREbzr4AuoBKyUeuE");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String info = reader.readLine();
            //todo by this point reading the json should be trivial so use that to read bar information and create bars.

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private Bar makeBar(){
        return null;
    }

    @Override
    public Map<Bar, Number> getBarsOrderMap() {
        return null;
    }
}
