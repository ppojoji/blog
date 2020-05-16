package naver.ppojoji.blog.dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEquality {

	@Test
	public void test_primitive_type() {
		// int, long, byte, float, double,...
		int a = 12;
		int b = 12;
		
		if( a == b) {
			System.out.println("OK");
		} else {
			System.out.println("????");
		}
	}
	
	@Test
	public void referenceType() {
		// 참조타입, 레퍼런스 타입은 값이 같은지 확인할때 equals...)
		Integer a = 33833883;
		Integer b = 33833883;
		if(a.equals(b)) {
			System.out.println("OK");
		} else {
			System.out.println("???");
		}
		
		String id = new String("abc");
		String id2 = new String("abc");
		
		if(id.equals(id2) ) {
			System.out.println("OK");
		}
		else {
			System.out.println("XXXXXX");
		}
	}

}
