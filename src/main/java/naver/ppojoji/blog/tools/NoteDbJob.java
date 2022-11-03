package naver.ppojoji.blog.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

/**
 * note의 origin_note값을 seq값으로 초기화함
 * @author ppojo
 *
 */
public class NoteDbJob {

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/blogdb?allowPublicKeyRetrieval=true&useSSL=false";
		String username = "bloguser";
		String password = "aYGks!240_";
		
		System.out.println(com.mysql.cj.jdbc.Driver.class); // 드라이버 등록하려고 그냥 찍음
//		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(url, username, password);
		
		PreparedStatement pst = con.prepareStatement("select seq from tb_note");
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Integer seq = rs.getInt("seq");
			System.out.println(seq);
			PreparedStatement update = con.prepareStatement("update tb_note set origin_note = ? where seq=?");
			update.setInt(1, seq);
			update.setInt(2, seq);
			
			update.executeUpdate();
		}
		
		con.close();
	}
}
