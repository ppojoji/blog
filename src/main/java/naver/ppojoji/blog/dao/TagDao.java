package naver.ppojoji.blog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.Tag;

@Repository
public class TagDao {

	@Autowired
	SqlSession session;
	public void bindTag(Integer postSeq, Integer tagSeq) {
		Tag tag = new Tag();
		
		tag.setSeq(tagSeq);
		tag.setPostSeq(postSeq);
		
		session.insert("TagMapper.bindTag",tag);
	}
	public List<Tag> findTagsByPost(Integer postSeq) {
		return session.selectList("TagMapper.findTagsByPost", postSeq);
	}
	
	public Tag TagSelect(String tagName) {
		return session.selectOne("TagMapper.tagSelect",tagName);
	}
	public Tag tagInsert(String tagName) {
		Tag tag = new Tag();
		tag.setTagName(tagName);
		/*
		 * tag.setSeq(6);
		 */
		session.insert("TagMapper.tagInsert", tag);
		return tag;
	}
	public List<Tag> tagByPost() {
		return session.selectList("TagMapper.tagByPost");
		
	}

}
