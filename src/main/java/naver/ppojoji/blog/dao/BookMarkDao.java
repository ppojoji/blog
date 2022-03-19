package naver.ppojoji.blog.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.User;

@Repository
public class BookMarkDao {
	@Autowired
	SqlSession session;

	public void addBookMark(Integer postSeq, User user) {
		Map<String,Object> map = new HashMap<>();
		map.put("postSeq", postSeq);
		map.put("userSeq", user.getSeq());
		
		session.insert("BookMarkMapper.addBookMark", map);
	}

	public void removeBookMark(Integer postSeq, User user) {
		Map<String,Object> map = new HashMap<>();
		map.put("postSeq", postSeq);
		map.put("userSeq", user.getSeq());
		
		session.delete("BookMarkMapper.removeBookMark", map);
		
	}

	public Integer readBookMark(int postSeq, User loginUser) {
		Map<String,Object> map = new HashMap<>();
		map.put("postSeq", postSeq);
		map.put("loginUser", loginUser);
		
		return session.selectOne("BookMarkMapper.readBookMark", map);
	}
}
