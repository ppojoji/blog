package naver.ppojoji.blog.service.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import naver.ppojoji.blog.BlogException;

@Service
public class OAuthService {

	private ObjectMapper om = new ObjectMapper();
			
	public String findUserEmail (String accessToken) {
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		String header = "Bearer " + accessToken; // Bearer 다음에 공백 추가
		Connection con = Jsoup.connect(apiURL);
		con.header("Authorization", header);
		con.ignoreContentType(true); // text/html, // appliction/json
		
		try {
			Document doc = con.get();
			System.out.println(doc.select("body").text());
			Map<String, Object> res = om.readValue(doc.select("body").text(), Map.class);
			System.out.println(res);
			res = (Map<String, Object>) res.get("response");
			return (String) res.get("email");
		} catch (IOException e) {
			throw new BlogException(503, "OAUTH_CONN_FAILED");
		}
	}
	
	public Object findUserProfile (String accessToken) {
//		String clientId = "";
		// String token = "YOUR_ACCESS_TOKEN"; // 네이버 로그인 접근 토큰;
        String header = "Bearer " + accessToken; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        /*
         * Authorization: Bearer adofkasdflkasdflksadflksdk
         * 
         */
        String responseBody = get(apiURL,requestHeaders);

        System.out.println(responseBody);
		return null;
	}
	
	private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    
    public static void main(String[] args) {
		String accessToken = "AAAAN1yJz_EMiDxGNB8sZb64ziOJmMnRW_AGib0hwUKzc7eahXZYELG5pE1sMmgJY4-2n1HKEQs9QwV0xJz-ulBslNM";
//		new OAuthService().findUserProfile(accessToken);
		new OAuthService().findUserEmail(accessToken);
	}
}
