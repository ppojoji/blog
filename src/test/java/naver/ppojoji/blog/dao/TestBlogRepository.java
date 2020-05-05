package naver.ppojoji.blog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import naver.ppojoji.blog.dto.LocalUpFile;
import naver.ppojoji.blog.dto.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestBlogRepository {

	@Autowired
	BlogDao blogRepo; 
	
	@Test
	public void test() {
		Post post = blogRepo.findPostBySeq(5025);
		assertNotNull(post);
		assertEquals(5025, post.getSeq().intValue());
		assertNotNull(post.getWriter()); 
		List<LocalUpFile>upfiles = post.getUpFiles();
		assertEquals(1, upfiles.size());
		System.out.println(upfiles.get(0));
		System.out.println(post);
		System.out.println(post.getOpen());
	}
}
