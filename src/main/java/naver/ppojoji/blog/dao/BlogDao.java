package naver.ppojoji.blog.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.BanHistory;
import naver.ppojoji.blog.dto.MultiSearch;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.Search;
import naver.ppojoji.blog.dto.Tag;
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
	/**
	 * 주어진 seq의 글 조회
	 * @param postSeq
	 * @return
	 */
	public Post findPostBySeq(int postSeq) {
		Post post = session.selectOne("BlogPostMapper.findPostBySeq", postSeq);
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

	public List<Post> findAllPost(boolean isOpen) {
		return session.selectList("BlogPostMapper.findAllPost",isOpen);
	}
	
	/**
	 * 내글 가져오기(관리용이므로 delYn 제외한 모든글 반환0
	 * @param searchType 
	 * @param adminUser 
	 * @return
	 */
	public List<Post> findAllByAdmin(String searchType, User adminUser) {
//		if(searchType.equals("all")) {
//			searchType = null;
//		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("adminUser", adminUser);
		
		System.out.println("### searchType " + searchType);
		return session.selectList("BlogPostMapper.findAllByAdmin",map);
	}
	
	public List<Post> findAllByAdmin(List<String> banTypes, User adminUser) {
//		if(searchType.equals("all")) {
//			searchType = null;
//		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("banTypes", banTypes);
		map.put("adminUser", adminUser);
		
		return session.selectList("BlogPostMapper.findAllByAdmin",map);
	}
	/**
	 * 금지된 글 목록 가져오기
	 * TODO BlogPostMapper.findAllByAdmin을 같이 사용하도록 수정하면 좋음.
	 * @param adminUser
	 * @return
	 */
	/*
	 * public List<Post> findBanByAdmin(String searchType , User adminUser) { Map
	 * map = new HashMap<>(); map.put("searchType", searchType);
	 * map.put("adminUser", adminUser);
	 * 
	 * return session.selectList("BlogPostMapper.findBanByAdmin",map); }
	 */
	
	public Integer insertPost(String title, String contents, Integer cateSeq , int writeSeq) {
		// 1. map 으로 감싸서 보내기
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("title", title);
		param.put("contents", contents);
		param.put("cateSeq", cateSeq);
		param.put("writeSeq", writeSeq);
		session.insert("BlogPostMapper.insertPost", param);
		
		System.out.println("gen seq: " + param.get("seq"));
		for (String k : param.keySet()) {
			System.out.println(" >> " + k + " : " + param.get(k));
		}
		BigInteger val = (BigInteger) param.get("seq");
		Long seq = val.longValue();
		return seq.intValue() ;
		// 
//		Post p = new Post();
//		p.setTitle(title);
//		p.setContents(contents);
//		session.insert("BlogPostMapper.insertPost", p);
		
		
	}
	/**
	 * 글 수정(이거 쓰지 마세요 updatePost(Post post) 쓰세요)
	 * @param title
	 * @param contents
	 * @param cateSeq
	 * @param postSeq
	 * @param loginUser 
	 */
	@Deprecated
	public void updatePost(String title, String contents, Integer cateSeq ,Integer postSeq, User loginUser ) {
		Map<String,Object> param = new HashMap<String, Object>(); 
		param.put("title", title);
		param.put("contents", contents);
		param.put("cateSeq", cateSeq);
		param.put("postSeq", postSeq);
		param.put("loginUser", loginUser);
		session.update("BlogPostMapper.updatePost",param);
	}
	public void updatePost(Post post) {
		session.update("BlogPostMapper.updatePost2", post);		
	}
	public void deletePost(Integer pid) {
		session.delete("BlogPostMapper.deletePost",pid);
	}

	public void viewCount(Integer pid) {
		session.update("BlogPostMapper.viewCount",pid);
		
	}

	public List<Post> readPosts(boolean open) {
		// FIXME 타입핸들러 넣으면 없어질 코드입니다.
		//String openChar = open ? "Y" : "N";
		return session.selectList("BlogPostMapper.open",open);
	}

	public void updateOpen(Integer postSeq, boolean isOpen) {
		//String openChar = isOpen ? "Y" : "N";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("postSeq", postSeq);
		map.put("isOpen", isOpen);
		session.update("BlogPostMapper.updateOpen",map);
		
	}

	public List<Post> findPostByWriter(Integer writerSeq) {
		return session.selectList("BlogPostMapper.findPostByWriter", writerSeq);
		
	}

	public void togglePost(Integer seq) {
		session.selectOne("BlogPostMapper.togglePost",seq);
		
	}

	public List<Post> searchPost(Search search) {
		return session.selectList("BlogPostMapper.SearchPost",search);
		
	}

	public List<Post> multiSearchPost(MultiSearch search) {
//		search.setTitle(true);
//		search.setContents(ture);
		return session.selectList("BlogPostMapper.MultiSearchPost",search);
	}

	public List<Post> delYn() {
		return session.selectList("BlogPostMapper.delYn");
	}

	public void setAsdeleted(Integer pid) {
		session.update("BlogPostMapper.setAsDeleted",pid);
		
	}
	/**
	 * 첨부파일을 하나 지움
	 * @param fileSeq
	 */
	public void deleteFile(Integer fileSeq) {
		session.delete("BlogPostMapper.deleteFile", fileSeq);
		
	}
	/**
	 * 주어진 카테고리의 글 조회(delYn 이 없으면 카테고리의 글 모두 조회) 
	 * @param cateSeq
	 * @param delYn
	 * @return
	 */
	public List<Post> findByCate(Integer cateSeq, String delYn) {
		Map<String,Object> map = new HashMap<>();
		map.put("seq", cateSeq);
		map.put("delYn", delYn);
		return session.selectList("BlogPostMapper.findByCate", map);
	}

	public List<Post> findByCateName(String cateName) {
		return session.selectList("BlogPostMapper.findByCateName",cateName);
	}
	
	public List<Post> findByCate2(Integer cateSeq) {
		return session.selectList("BlogPostMapper.findByCate2", cateSeq);
	}

	public int postDelete(Post post) {
//		Map map = new HashMap<>();
//		map.put("adminUser", adminUser.getEmail());
//		map.put("pid", pid);
//		
		return session.delete("BlogPostMapper.postDelete", post);
	}

	public List<Post> findpostsDelY() {
		return session.selectList("BlogPostMapper.findpostsDelY");
	}

	public void setPostPolicy(Integer postSeq, String code) {
		Map map = new HashMap<>();
		map.put("postSeq", postSeq);
		map.put("code", code);
		
		session.update("BlogPostMapper.setPostPolicy",map);
		
	}
}
