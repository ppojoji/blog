package naver.ppojoji.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.BanHistory;
import naver.ppojoji.blog.dto.Post;

@Repository
public class BanHistoryDao {

	@Autowired 
	SqlSession session;

	public void Banhistory(BanHistory dto) {
		session.insert("BanHistoryMapper.ban",dto);
		
	}

	public List<BanHistory> GetBanHistoryByPost(Post post) {
		List<BanHistory> ban = session.selectList("BanHistoryMapper.GetBanHistoryByPost",post);
		return ban;
	}

	public BanHistory findRecentBan(Post post) {
		BanHistory ban = session.selectOne("BanHistoryMapper.findRecentBan",post);
		return ban;
	}
}
