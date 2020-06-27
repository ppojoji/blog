package naver.ppojoji.blog.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.OauthTokenDao;
import naver.ppojoji.blog.dto.OauthToken;

@Service
public class OauthTokenService {
	@Autowired
	OauthTokenDao oauthTokenDao;
		
	public OauthToken getToken(int seq) {
		return oauthTokenDao.getToken(seq);
	}

	public void saveToken(int userSeq, Map<String, String> tokens) {
		String expired = tokens.get("expires_in");
		Integer duration = Integer.parseInt(expired);
		// FIXME 날짜로 변환이 안되는 중
		duration /= 1000 ; // second
		tokens.put("sec", duration.toString());
		oauthTokenDao.saveToken(userSeq,tokens);
	}

}
