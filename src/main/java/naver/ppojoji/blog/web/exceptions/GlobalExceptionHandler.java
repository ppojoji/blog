package naver.ppojoji.blog.web.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import naver.ppojoji.blog.BlogException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BlogException.class)
	public Object handleException(BlogException e) {
		int code = e.getResponseCode();
		HttpStatus status = findStatus(code);
		
		Map<String, Object> body = new HashMap<>();
		body.put("success", false);
		body.put("cause", e.getMessage());
		ResponseEntity<Map<String, Object>> res = new ResponseEntity<Map<String,Object>>(body, status);
		return res;
	}
	
	private HttpStatus findStatus(int code) {
		HttpStatus [] values = HttpStatus.values();
		for (int i = 0; i < values .length; i++) {
			if (code == values[i].value()) {
				return values[i];
			}
		}
		return HttpStatus.BAD_REQUEST;
	}
}
