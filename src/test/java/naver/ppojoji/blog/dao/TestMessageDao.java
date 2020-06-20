package naver.ppojoji.blog.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import naver.ppojoji.blog.dto.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestMessageDao {

	@Autowired MessageDao mDao;
	
	@Test
	public void test_메세지쓰기() {
		Message message = new Message();
		message.setSender("aaaa");
		message.setReceiver(204);
		message.setContent("bbbb");
		
		mDao.insert(message);
	}
	
	@Test
	public void test_내메세지읽어오기() {
		List<Message> messages = null;
		/*
		 * User loginUser = session.(....)
		 * loginuser.getSeq()7
		 */
		messages = mDao.findMessages(204);
		System.out.println(messages.toString());
	}
	
}
