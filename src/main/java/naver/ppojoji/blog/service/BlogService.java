package naver.ppojoji.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import naver.ppojoji.blog.dao.BlogDao;
import naver.ppojoji.blog.dao.FileDao;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.web.Value;

@Service
public class BlogService {
	@Autowired
	BlogDao blogDao;
	
	@Autowired
	FileUploadService fileService;
	/*
	List<Post> list = new ArrayList<>();
	{
		Post fakePost0 = new Post(300L, "테스트 제목", "본문 내용",  new Date(),322);
		Post fakePost1 = new Post(302L, "DAFSD", "GDASFADS",  new Date(),3433);
		
		list.add(fakePost0);
		list.add(fakePost1);
	}
	blogService.findAllPosts("N");
	*/
	public List<Post> findAllPosts(String isOpen) {
		return blogDao.findAllPost(isOpen);
	}

	public Post readPosts(int seq, boolean updateCount) {
		if (updateCount) {
			blogDao.viewCount(seq);			
		}
		return blogDao.findPostBySeq(seq);
		/*
		for(int i=0; i<list.size(); i++) {
			Post post = list.get(i);
			if(post.getSeq().intValue()==seq) {
				return post;
			}
		}
		// here !!
		return null;
		*/
		// throw new RuntimeException("없다!");
		
	}
	/**
	 * 새로운 글 추가
	 * @param title
	 * @param contents
	 */
	public void insertPost(String title, 
			String contents, 
			List<MultipartFile> files,
			Integer writerSeq) {
			// HttpSession session) {
		Integer postSeq = blogDao.insertPost(title,contents, writerSeq);
		fileService.uploadSave(postSeq, files);
		
		//long seq = nextSeq();
		//list.add(new Post(seq, title, contents, new Date(), 0));
	}
	/*
	 * 나중에 없앨겁니다.
	 */
	/*
	private long nextSeq() {
		return list.get(list.size()-1).getSeq() + 1;
	}
	*/
	/**
	 * 수정
	 * @param title
	 * @param contents
	 */
	public void updatePost(String title, String contents ,Integer postSeq) {
		blogDao.updatePost(title,contents,postSeq);
	}

	public void deletePost(Integer pid) {
		blogDao.deletePost(pid);
	}

	public List<Post> readPosts(boolean open) {
		return blogDao.readPosts(open);
	}

	public void updateOpen(Integer postSeq, boolean isOpen) {
		blogDao.updateOpen(postSeq,isOpen);
		
	}
}
