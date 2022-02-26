package naver.ppojoji.blog.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import naver.ppojoji.blog.dto.Tag;
import naver.ppojoji.blog.service.TagService;

@Controller
public class TagController {
	@Autowired
	TagService tagServise;
	
	/**
	 * 주어진 tagName에 해당하는 태그를 조회함
	 * @param tagName
	 * @return
	 */
	@GetMapping("/api/tagSelect/{tagName}")
	@ResponseBody
	public Object TagSelect(@PathVariable String tagName) {
		
		Tag tag = tagServise.tagInsert(tagName);
		Map<String, Object> res = new HashMap<>();
		res.put("tag", tag);
		res.put("success", true);
		return res;
	}
	@GetMapping("/api/postBytag")
	@ResponseBody
	public List<Tag> tagByPost(){
		return tagServise.tagByPost();
	}
}
