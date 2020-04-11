package naver.ppojoji.blog.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import naver.ppojoji.blog.dao.BlogDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestConnection {

	@Autowired DataSource ds;
	@Autowired BlogDao blogRepo;
	
	@Test
	public void test_연결잘되는지테스트() throws SQLException {
		assertNotNull(ds);
		// blogDao.
		
	}
	/*
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521/orcl";
		String user = "board";
		String password = "board";
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver() );
		
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement stmt = con.prepareStatement("select board_no from tb_board");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("board_no"));
		}
		
		
		con.close();
		
		
	}
	*/
}
