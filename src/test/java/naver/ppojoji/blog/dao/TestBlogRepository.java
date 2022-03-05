package naver.ppojoji.blog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import naver.ppojoji.blog.dto.Category;
import naver.ppojoji.blog.dto.LocalUpFile;
import naver.ppojoji.blog.dto.MultiSearch;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.service.BlogService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestBlogRepository {

	@Autowired
	BlogDao blogRepo;
	
	@Autowired
	BlogService blogService;
	
	@Test
	@Ignore
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
		System.out.println("view pass " + post.getViewPass());
	}
	
	@Test
	public void search() {
		MultiSearch search = new MultiSearch(); 
//		search.setTitle(true);
		search.setContents(true);
		search.setWriter(true);
		search.setMultiKeyword("파일");
		List list = blogService.multiSearchPost(search);
		System.out.println(list);
		
		org.apache.ibatis.type.JdbcType D;
	}
	
	@Test
	public void test_카테고리_포함해서_읽기() {
		Post post = blogRepo.findPostBySeq(5009);
		User user = post.getWriter();
		assertNotNull(user);
		Category cate = post.getCategory();
		assertNotNull(cate);
		assertEquals(3, cate.getSeq().intValue());
		assertEquals("ccc", cate.getName());
	}
	
	@Test
	public void test_delYn() {
		List<Post> post = blogService.findpostsDelY();
		System.out.println("[count] " + post.size());
	}
}
