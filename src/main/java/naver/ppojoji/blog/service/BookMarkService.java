package naver.ppojoji.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.ppojoji.blog.dao.BookMarkDao;
import naver.ppojoji.blog.dto.User;

@Service
@Transactional
public class BookMarkService {

	@Autowired
	BookMarkDao bookMarkDao;
	/**
	 * 사용자가 주어진 글을 북마크에 추가함
	 * @param postSeq
	 */
	public void addBookMark(Integer postSeq, User user) {
		bookMarkDao.addBookMark(postSeq,user);
	}
	/**
	 * 사용자가 주어진 글을 북마크에서 삭제함
	 */
	public void removeBookMark(Integer postSeq, User user) {
		bookMarkDao.removeBookMark(postSeq,user);
	}
	public Integer readBookMark(int postSeq, User loginUser) {
		
		Integer cnt = bookMarkDao.readBookMark(postSeq,loginUser);


		return cnt;// 북마크 없다!
		
	}
	
	
}
