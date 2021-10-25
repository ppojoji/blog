package naver.ppojoji.blog.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/api/cate")
	@ResponseBody
	public Object insertCate(@RequestParam String cate) {
		Category cateObj = cateSerivce.insertCate(cate);
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		res.put("cate", cateObj);
		return res;
	}
	// api/cate/4553/name
	// {value: 'vue.js'}
	@PutMapping("/api/cate/{cateSeq}/name")
	@ResponseBody
	public Object updateCate(@PathVariable Integer cateSeq, @RequestBody Map<String, Object> param) {
//		Category cateObj = cateSerivce.insertCate(cate);
		
		System.out.println("[cate]" + cateSeq);
		System.out.println("[body]" + param);
		String cateName = (String)param.get("value"); // "abc"
		
		Category cate = cateSerivce.updateCate(cateSeq,cateName);
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		res.put("cate", cate);
		return res;
	}
	@DeleteMapping("api/cate/{cateSeq}")
	@ResponseBody
	public Object deleteCate(@PathVariable Integer cateSeq) {
		cateSerivce.deleteCate(cateSeq);
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		return res;
	}
	@PutMapping("api/cate/order/{srcCateSeq}/{dstCateSeq}")
	@ResponseBody
	public Object orderChange(@PathVariable Integer srcCateSeq ,@PathVariable Integer dstCateSeq ) {
		cateSerivce.orderChange(srcCateSeq,dstCateSeq);
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		return res;
	}
}
