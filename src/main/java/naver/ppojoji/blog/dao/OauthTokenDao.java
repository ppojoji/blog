package naver.ppojoji.blog.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.OauthToken;

@Repository
public class OauthTokenDao {
	@Autowired
	SqlSession session;
	
	public OauthToken getToken(int seq) {
		return session.selectOne("OauthTokenMapper.getToken", seq);
	}

	public void saveToken(int userSeq, Map<String, String> tokens) {
		tokens.put("onwer", "" + userSeq);
		System.out.println("PARAM: " + tokens.toString());
		session.insert("OauthTokenMapper.saveToken", tokens);
	}

}
