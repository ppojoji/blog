package naver.ppojoji.blog.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import naver.ppojoji.blog.dto.Category;
import naver.ppojoji.blog.service.CategoryService;

@Controller
public class CateController {
	@Autowired CategoryService cateSerivce;
	/**
	 * 모든 카테고리 조회
	 * @return
	 */
	@GetMapping("/api/cates")
	@ResponseBody
	public Object listAllCategory() {
		List<Category> cates = cateSerivce.findAllCate();
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("cates", cates);
		res.put("success", true);
		return res;
	}
}
