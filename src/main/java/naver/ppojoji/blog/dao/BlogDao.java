package naver.ppojoji.blog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.User;

/**
 * XXXDao
 * XXXRepository - 디비로 쿼리 날리고 받아오는 일이 다 이곳에 있음
 * @author HA
 *
 */
@Repository
public class BlogDao {
	
	// CRUD 연산
	// C - INSERT INTO ... 
	// R - SELECT .. FROM ...
	// U - UPDATE .. SET .... WHERE
	// D - DELETE .... WHERE ...
	// @Autowired DataSource ds;
	@Autowired SqlSession session;
	
	@Autowired UserDao userDao;
	
	/*
	public void getCon() throws SQLException {
		Connection con = ds.getConnection();
		con.close();
		System.out.println("done");
	}
	*/
	public Post findPostBySeq(int postSeq) {
		Post post = session.selectOne("BlogPostMapper.findPostBySeq", postSeq);
		Integer writerSeq = post.getWriterSeq();
		User writer = userDao.findUser(writerSeq); // !!!
		post.setWriter(writer);
		return post;
		// 쌩 쿼리 - XXXXXXXXX
		/*
		Connection con = null; // 1. 디비와 전화 연결을 함
		PreparedStatement stmt = con.prepareStatement("select * from TB_BLOGPOST where seq = ?");
		stmt.setInt(1, postSeq);
		
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return null;
		} else {
			return null;
		}
		
		// JdbcTemplate jdbc; 스프링에서 제공하는 라이브러리(2)
		
		// MyBatis ; 외부 라이브러리(1)
		
		// Hibernate:(3) - 디비 모델링을 되게 잘해야 함!
		 * 
		 */
	}

	public List<Post> findAllPost() {
		return session.selectList("BlogPostMapper.findAllPost");
	}

	public void insertPost(String title, String contents) {
		// FIXME 글쓴 사람에 대한 연결 정보가 없어서 BlogMapper.xml 파일에 201번 사용자를 지정해 놓았습니다.
		// 1. map 으로 감싸서 보내기
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("title", title);
		param.put("contents", contents);
		session.insert("BlogPostMapper.insertPost", param);
		// 
//		Post p = new Post();
//		p.setTitle(title);
//		p.setContents(contents);
//		session.insert("BlogPostMapper.insertPost", p);
		
		
	}

	public void updatePost(String title, String contents, Integer postSeq) {
		Map<String,Object> param = new HashMap<String, Object>(); 
		param.put("title", title);
		param.put("contents", contents);
		param.put("postSeq", postSeq);
		session.update("BlogPostMapper.updatePost",param);
	}

	public void deletePost(Integer pid) {
		session.delete("BlogPostMapper.deletePost",pid);
	}

	public void viewCount(Integer pid) {
		session.update("BlogPostMapper.viewCount",pid);
		
	}

}
