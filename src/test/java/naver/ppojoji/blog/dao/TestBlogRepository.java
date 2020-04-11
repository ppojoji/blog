package naver.ppojoji.blog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import naver.ppojoji.blog.dao.BlogDao;
import naver.ppojoji.blog.dto.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestBlogRepository {

	@Autowired
	BlogDao blogRepo; 
	
	@Test
	public void test() {
		Post post = blogRepo.findPostBySeq(5001);
		assertNotNull(post);
		assertEquals(5001, post.getSeq().intValue());
		System.out.println(post);
	}
}
