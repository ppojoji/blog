package naver.ppojoji.blog.dao;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TestStationListing {
	public static void main(String[] args) throws IOException { 
		String uri = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getMsrstnList" 
		+ "?serviceKey=@key" 
		+ "&numOfRows=100" 
		+ "&pageNo=1" 
		+ "&addr=@sido" 
		+ "&stationName="; 
		String key = "RHJDYwDpuFiFtejEpchEWBkx8Uy8XrZVPSkmTOd%2BVk2qUO7t8HOUGnBHj63GhpiHfWxgxEYQy0MeQFK2Ysh6kg%3D%3D"; 
		String url = uri.replace("@key", key).replace("@sido", "서울"); 
		Document doc = Jsoup.connect(url).get(); 
		System.out.println(doc.toString()); 
		} 
	} 	

