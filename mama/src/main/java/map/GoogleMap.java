package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;



public class GoogleMap {

   public static void main(String[] args) throws IOException {

	   GoogleMap.getGeoDataByAddress("서울");
   }
   private static Map<String, String> getGeoDataByAddress(String completeAddress) {
       try {
           String API_KEY = "AIzaSyDXMN6Lg3WVVzi20NcbAEEBJu6Xw9Ai3Cs";
           String surl = "https://maps.googleapis.com/maps/api/geocode/json?address="+URLEncoder.encode(completeAddress, "UTF-8")+"&key="+API_KEY;
           URL url = new URL(surl);
           InputStream is = url.openConnection().getInputStream();
    
           BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
           
           StringBuilder responseStrBuilder = new StringBuilder();
           
           String inputStr;
           
           System.out.println(">>>>>>>>>> >>>>>>>>>> InputStream Start <<<<<<<<<< <<<<<<<<<<");
           while ((inputStr = streamReader.readLine()) != null) {
               System.out.println(">>>>>>>>>>     "+inputStr);
               responseStrBuilder.append(inputStr);
           }
           System.out.println(">>>>>>>>>> >>>>>>>>>> InputStream End <<<<<<<<<< <<<<<<<<<<");
    
           JSONObject jo = new JSONObject(responseStrBuilder.toString());
           JSONArray results = jo.getJSONArray("results");
           System.out.println(jo.get("status"));
           
           Map<String, String> ret = new HashMap<String, String>();
           if(results.length() > 0) {
               JSONObject jsonObject;
               jsonObject = results.getJSONObject(0);
               System.out.println(jsonObject);
               JSONObject a = jsonObject.getJSONArray("address_components").getJSONObject(0);
               Double lat = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
               Double lng = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
               ret.put("lat", lat.toString());
               ret.put("lng", lng.toString());
               System.out.println("LAT:\t\t"+lat);
               System.out.println("LNG:\t\t"+lng);
               JSONArray ja = jsonObject.getJSONArray("address_components");
               
               System.out.println(ja.get(0));
               System.out.println(ja.get(1));
               
               return ret;
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }

   
   

}