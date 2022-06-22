package map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class NaverMap {
	public double[] getCoordinate(String address) {
		// 주소 입력 -> 위도, 경도 좌표 추출.
		// 네이버  API clientId
		String clientId = "mj1uygntyw";
		// 네이버 API clientKey
		String clientSecret = "vOrqSObb6wUrijya4VzVHGCVDN8rMNAH1JrHSLO4";
		
		try {
			//매개변수로 가져온 address 변수를 UTF-8로 인코딩함
			String addr = URLEncoder.encode(address, "UTF-8");
			
			// Geocoding 개요에 나와있는 API URL 입력.
			String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr;	// JSON
			
			//apiURL을 담은 URL 객체생성
			URL url = new URL(apiURL);
			//url을 HttpURLConnection에 담으면 더 많은 기능을 사용할수 있으므로 HttpURLConnection타입 변수에 담아줌
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			// Geocoding 개요에 나와있는 요청 헤더 입력, clientId, client Secret
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			
			// 요청 결과 확인. 정상 호출인 경우 200
			int responseCode = con.getResponseCode();
			
			BufferedReader br;
			
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			
			StringBuffer response = new StringBuffer();
			
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			
			br.close();
			
			JSONTokener tokener = new JSONTokener(response.toString());
			JSONObject object = new JSONObject(tokener);
			//JSON 형태 addresses배열을 JSon arr변수에 담음
			JSONArray arr = object.getJSONArray("addresses");
			System.out.println(arr);
			double[] japyo = new double[2];
			for (int i = 0; i < arr.length(); i++) {
				JSONObject temp = (JSONObject) arr.get(i);
				System.out.println("address : " + temp.get("roadAddress"));
				System.out.println("jibunAddress : " + temp.get("jibunAddress"));
				japyo[0] = temp.getDouble("y");
				System.out.println("위도 : " + temp.get("y"));
				japyo[1] = temp.getDouble("x");
				System.out.println("경도 : " + temp.get("x"));
			}
			return japyo;
			
		} catch (Exception  e) {
			System.out.println(e);
		}
		return null;
	}
}