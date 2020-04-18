package naver.ppojoji.blog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import naver.ppojoji.blog.dto.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestUserDao {

	@Autowired
	UserDao userDao;
	
	@Test
	public void test() {
		User user = userDao.findUser(204);
		assertNotNull(user);
		assertEquals(204, user.getSeq());
		assertEquals("test2@naver.com", user.getEmail());
		
	}

}
