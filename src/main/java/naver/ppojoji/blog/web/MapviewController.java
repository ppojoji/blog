package naver.ppojoji.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class MapviewController { 
	@GetMapping("/mapView") 
	public String pageMapView() { 
		return "mapView" ;
		} 
	} 