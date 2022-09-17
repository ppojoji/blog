package naver.ppojoji.blog;

import java.util.HashMap;
import java.util.Map;

public class Res {

	public static Map<String, Object> success(String key, Object value) {
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		res.put(key, value);
		return res;
	}
}
