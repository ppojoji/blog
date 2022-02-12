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
}
