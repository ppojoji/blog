package naver.ppojoji.blog.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import naver.ppojoji.blog.dto.Mail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TesetMailingDao {

	@Autowired
	MailingDao dao;
	
	@Test
	public void test_메일잡_읽기() {
		List<Mail> jobs = dao.getBeforeMail();
		for (Mail job : jobs) {
			System.out.println(job);
		}
	}
}
