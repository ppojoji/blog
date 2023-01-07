package naver.ppojoji.blog.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.Util;
import naver.ppojoji.blog.dto.User;

@Repository
public class UserDao {
	@Autowired SqlSession session;
	/**
	 * pk 로 사용자 정보 조회. 사용자 기본 정보만 반환함
	 * @param userSeq
	 * @return
	 */
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
	
	public void userDelete(User user, String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
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
	public int UpdatePwd(String email,String pwd) {
		Map<String, Object> user =Util.params("email",email ,"pwd",pwd);
		return session.update("UserMapper.updatePwd", user);
	}
	public void join(User user) {
//		Map map = new HashMap<String, Object>();
//		map.put("id", id);
//		map.put("pwd", pwd);
//		map.put("email", email);
//		map.put("pwhint", pwhint);
//		map.put("pwhintans", pwhintans);
		
		session.insert("UserMapper.join",user);
	}
	
	public User hint(User searchOption) {
		return session.selectOne("UserMapper.hint",searchOption);
	}
	public void updateReadNote(User user, Integer readNote) {
		/* map 안써도 됨*/
//		Map map = new HashMap<>();
//		map.put("userSeq",user);
//		map.put("readNote",readNote);
		user.setRead_note(readNote);
		
		session.update("UserMapper.updateReadNote",user);
	}
	public void updateProfile(User user) {
		session.update("UserMapper.updateProfile",user);
	}
}
