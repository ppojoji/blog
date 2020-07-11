package naver.ppojoji.blog.service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dto.Post;

@Service
public class PostDeletion {

	DateTimeFormatter df = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
	@Autowired
	BlogService service;
	@Scheduled(cron = "0 0 2 * * *")
	public void deletePost() {
		// main ...
		List<Post> list = service.delYn();
		//System.out.println(" # of post: " + list.size());
		for(int i=0;i<list.size();i++) {
			Post p = list.get(i);
			service.deletePost(p.getSeq());
		}
	}
	
	
}
