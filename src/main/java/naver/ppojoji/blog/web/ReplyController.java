package naver.ppojoji.blog.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.Reply;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.BanReporterService;
import naver.ppojoji.blog.service.BlogService;
import naver.ppojoji.blog.service.ReplyService;

@Controller
public class ReplyController {
	@Autowired
	BanReporterService banService;
	
	@Autowired
	ReplyService replyServise;
	@Autowired
	BlogService blogServise;
	
	@RequestMapping(value = "/article/replyPost",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replyPost(
			@RequestParam Integer parent,
			@RequestParam String contents,
			HttpSession session
			){
		String title = null;
		String writer = null;
		String pwd = null;
		
		User user = Util.getUser(session);
		
		Reply reply = new Reply(null, title, contents, user, pwd, parent);
		replyServise.replyInsert(reply);
		/*
		 * http://localhost:8080/blog/article/pageReadPost/5013
		 */
		Map<String, Object> res = new HashMap<>();
		res.put("reply", reply);
		res.put("success", true);
		return res;
	}
	
	@RequestMapping(value = "article/replies/{seq}" , method = RequestMethod.GET)
	@ResponseBody
	public List<Reply> selectReply(@PathVariable Integer seq) {
		List<Reply> reply = replyServise.selectReply(seq);
		return reply;
	}
	
	@DeleteMapping("article/reply/{seq}")
	@ResponseBody
	public Object removeReply(HttpSession session,  @PathVariable Integer seq) {
		// TODO 답글 지움 로직 필요함
		// 지웠다고 가저하고...
		User user = Util.getUser(session);
		
		replyServise.replyDelete(user,seq);
		
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		res.put("seq", seq);
		return res;
		
	}
	
	@RequestMapping(value="/article/reply" ,method = RequestMethod.GET)
	public String reply(HttpServletRequest req, HttpSession session, @RequestParam Integer pid) {
		Post post = blogServise.readPosts(pid, false);
		// model.setAttribute("", post);
		req.setAttribute("post", post);
		return "reply";
	}
	
	@RequestMapping(value="/article/reply/{replySeq}/ban/{banCode}" ,method = RequestMethod.POST)
	@ResponseBody
	public Object Ban(HttpSession session, @PathVariable Integer replySeq, @PathVariable String banCode) {
		User user = Util.getUser(session);
		banService.insertBan(user, replySeq, banCode);
		
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		return res;
	}
}
