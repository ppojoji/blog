package naver.ppojoji.blog.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.User;

@Repository
public class UserDao {
	@Autowired SqlSession session;
	public User findUser(int userSeq) {
		return session.selectOne("UserMapper.findUser",userSeq);
	}
	public User login(String id, String pwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd", pwd);
		return session.selectOne("UserMapper.login", map);
		
	}
}
