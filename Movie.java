import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Movie {

    private static final String clientId =  "lT4aeMQ7_c1wfinlkVRD";
    private static final String clientSecret = "NpR5fBPQoc";

    public static void main(String[] args) throws IOException, ParseException {

        System.out.print("검색어를 입력하세요: ");
        BufferedReader q = new BufferedReader(new InputStreamReader(System.in));
        String keyword = q.readLine();

        String text = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="+text;
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

        int responseCode = con.getResponseCode();
        BufferedReader br;
        if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else { // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null){
            response.append(inputLine);
        }
        br.close();

        JSONParser jsonParser =  new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new String(response));
        JSONArray infoArray = (JSONArray) jsonObject.get("items");

        for(int i=0;  i<infoArray.size(); i++){
            System.out.println("=item_"+ i +" =====================================");
            JSONObject itemObject = (JSONObject) infoArray.get(i);
            System.out.println("title:\t" + itemObject.get("title"));
            System.out.println("subtitle:\t" + itemObject.get("subtitle"));
            System.out.println("director:\t" + itemObject.get("director"));
            System.out.println("actor:\t" + itemObject.get("actor"));
            System.out.println("userRating:\t" + itemObject.get("userRating") + "\n");
        }
    }
}
