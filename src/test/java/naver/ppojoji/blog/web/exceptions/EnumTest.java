package naver.ppojoji.blog.web.exceptions;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;

public class EnumTest {

	@Test
	public void test() {
		HttpStatus[] values = HttpStatus.values();
		
		int code = 400;
		for (int i = 0; i < values.length; i++) {
			System.out.println(">> " + values[i].value());
		}
	}

}
