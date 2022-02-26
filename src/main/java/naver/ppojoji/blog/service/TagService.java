package naver.ppojoji.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.TagDao;
import naver.ppojoji.blog.dto.Tag;

@Service
public class TagService {

	@Autowired
	TagDao tagDao;

	public void bindTags(Integer postSeq, List<Integer> tagSeqs) {
		for (Integer tagSeq : tagSeqs) {
			tagDao.bindTag(postSeq,tagSeq);
		}
	}
	
	public List<Tag> findTagsByPost(Integer postSeq){
		return tagDao.findTagsByPost(postSeq);
	}
	
	/**
	 * 태그 조회
	 * @param tagName
	 * @return
	 */
	public Tag TagSelect(String tagName) {
		return tagDao.TagSelect(tagName);
	}
	/**
	 * 주어진 태그 문자열로 검색한 후 있으면 반환하고, 없으면 새로 추가한 후 반환
	 * @param tagName
	 * @return
	 */
	public Tag tagInsert(String tagName) {
		Tag tag =  tagDao.TagSelect(tagName);
		if(tag == null) {
			// FIXME 고칠 곳이 있음(쿼리)
			tag = tagDao.tagInsert(tagName);
			System.out.println("[새 태그 입력] " + tag);
		}
		return tag;
	}

	public List<Tag> tagByPost() {
		
		return tagDao.tagByPost();
	}
}
