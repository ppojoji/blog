package naver.ppojoji.blog;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import naver.ppojoji.blog.dto.Post;

public class AboutJackson {

	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		Post p = new Post();
		p.setSeq(3222L);
		p.setTitle("dakdk");
		p.setContents("doadkf aodsfkaldfk");
		p.setCreationDate(new Date());
		
		String json = om.writeValueAsString(p);
		System.out.println(json);
	}
}
