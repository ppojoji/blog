package naver.ppojoji.blog.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.Message;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.MessageService;

@Controller
public class MessageController {
	@Autowired 
	MessageService messageService; 
	
	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value = "/api/Message" , method = RequestMethod.GET, produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public Object readMessage(HttpSession session) {
		User loginUser = Util.getUser(session);
		List<Message> message = messageService.findMessages(loginUser.getSeq());
		
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		res.put("messages", message);
		return res;
	}
	
	@RequestMapping(value="/article/api/messageDelete", method= RequestMethod.POST, produces =Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public String deletePost(@RequestParam Integer seq) throws JsonProcessingException {
System.out.println("##seq"+seq);
		this.messageService.deleteMessage(seq);
		Map<String, Object> res = new HashMap<>();
		System.out.println("##res"+res);
		res.put("success",true);
		return om.writeValueAsString(res); 
	}
	
	@RequestMapping(value="/note/writeMessage/{receiver}", method = RequestMethod.GET)
	public String writeMessage(Model model ,@PathVariable Integer receiver) {
		model.addAttribute("receiver", receiver);
		return "message";
	}
	@RequestMapping(value = "/note/sendMessage", method = RequestMethod.POST ,produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public Object sendMessage(
			@RequestParam String content ,
			@RequestParam String sender ,
			@RequestParam Integer receiver,
			HttpSession session) {
		 User loginUser = Util.getUser(session);
		 int userSeq = loginUser.getSeq();
		 
		 System.out.println("### loginUser " + loginUser);
		 System.out.println("### userSeq " + userSeq);
		 System.out.println("### receiver " + receiver);
		
		Message msg = new Message();
			msg.setContent(content);
			msg.setSender(sender);
			msg.setReceiver(receiver);
		System.out.println(msg);
	
		if(userSeq != receiver) {
			messageService.sendMessage(msg);
			return Util.success("data", msg);
		} else {
			return Util.fail("casue", "SAME_ID");
		}
	}
	
	@RequestMapping(value = "/article/api/messageRead/{msgSeq}", method = RequestMethod.GET, produces = Value.APPLICATION_JSON_CHARSET_UTF_8)
	@ResponseBody
	public Object ReadMessage(@PathVariable Integer msgSeq) {
		Message msg = messageService.redaMessages(msgSeq);
//		Map<String, Object> res = new HashMap<>();
//		res.put("success", true);
//		res.put("msg", msg);
		return Util.success("msg", msg);
	}
	
	@RequestMapping(value = "/api/replyMessage" ,method = RequestMethod.POST)
	@ResponseBody
	public Object ReplyMessage(@RequestParam String title , @RequestParam String content, @RequestParam Integer messageSeq) {
		
		messageService.replyMessage(title,content,messageSeq);
		return Util.success("data", null);
	}
}
