package naver.ppojoji.blog.dao;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TestMyImageList {
	public static void main(String[] args) throws IOException { 
		 String accessToken = "eaaf24b88ea86a962053b8e09c1be8fc58a79212";
//		String accessToken = "38bcb3277a39f5dcadb62cfe8b8417957edf78b8";
		String url = "https://api.imgur.com/3/account/me/images"; 
		Document doc = null; 
		doc = Jsoup.connect(url) 
				.ignoreContentType(true) 
				.ignoreHttpErrors(true) 
				.header("Authorization", "Bearer " + accessToken) 
				.get(); 
		
		System.out.println(doc.toString()); 
	} 
}
