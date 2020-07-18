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
		return session.selectOne("UserMapper.findUserV1",userSeq);
	}
	public User login(String id, String pwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd", pwd);
		return session.selectOne("UserMapper.login", map);
	}
	public User findUserByLoginKey(String autoLoginKey) {
		return session.selectOne("UserMapper.autoLoginKey", autoLoginKey);
	}
	
	public void userDelete(String id, String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("email", email);
		session.delete("UserMapper.userDelete", map);
	}
	public User findUserById(String userId) {
		return session.selectOne("UserMapper.findUserId", userId);
	}
	public void updateAutoLoginKey(int seq, String autoLoginKey) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq",seq);
		map.put("autoLoginKey", autoLoginKey);
		session.update("UserMapper.updateAutoLoginKey", map);
	}
	public User findUserByEmail(String email) {
		return session.selectOne("UserMapper.findUserEmail", email);
	}
}
