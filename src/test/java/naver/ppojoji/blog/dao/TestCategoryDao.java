package naver.ppojoji.blog.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import naver.ppojoji.blog.dto.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestCategoryDao {

	@Autowired
	CategoryDao dao;
	
	@Test
	public void test() {
		Category cate = dao.findCategory(1);
		
		System.out.println(cate.getName());
	}

}
