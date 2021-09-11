package naver.ppojoji.blog;

public class Integer_VS_int {

	public static void main(String[] args) {
		
		int v = 444;
		Integer a = v;
		int z = a.intValue();
		
		// 1.5
		
		Integer xy = v;
		
		// autoboxing 오토박싱
		
		xy = null;
		int abc = xy.intValue(); // null pointer exception
		
		
	}
}
