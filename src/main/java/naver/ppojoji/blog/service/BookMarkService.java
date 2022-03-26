package naver.ppojoji.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.ppojoji.blog.dao.BookMarkDao;
import naver.ppojoji.blog.dto.Post;
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
	/**
	 * 사용자가 특정 글 북마크 했는지 조회
	 * @deprecated Post.bookmarked 안에 북마크 여부를 표시하도록 수정한 후에 사용하지 않음
	 * @param postSeq
	 * @param loginUser
	 * @return
	 */
	public Integer readBookMark(int postSeq, User loginUser) {
		
		Integer cnt = bookMarkDao.readBookMark(postSeq,loginUser);


		return cnt;// 북마크 없다!
		
	}
	/**
	 * 주어진 사용자의 북마크 글들 조회
	 * @param userSeq 조회할 사용자 PK
	 * @return
	 */
	public List<Post> loadBookMarks(Integer userSeq) {
		List<Post> posts = null;
		posts = bookMarkDao.loadBookMarks(userSeq);
		return posts;
	}
	
	
}
