package naver.ppojoji.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import naver.ppojoji.blog.dao.BlogDao;
import naver.ppojoji.blog.dao.FileDao;
import naver.ppojoji.blog.dao.UserDao;
import naver.ppojoji.blog.dto.MultiSearch;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.Search;
import naver.ppojoji.blog.dto.User;
import naver.ppojoji.blog.web.Value;

@Service
public class BlogService {
	@Autowired
	BlogDao blogDao;
	
	@Autowired
	FileUploadService fileService;
	
	@Autowired 
	UserDao userDao;
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
	//public List<Post> findAllPosts(String isOpen) {
	/**
	 * 공개글을 조회함
	 * @param isOpen
	 * @return
	 */
	public List<Post> findAllPosts(boolean isOpen) {
		List<Post> posts = blogDao.findAllPost(isOpen);
		// 1 비번을 "*****" 로 뭉게버림
		for(int i=0;i<posts.size();i++) {
			posts.get(i);
			if(posts.get(i).getViewPass() != null) {
				posts.get(i).setViewPass("*************");
			}
		}
		return posts;
	}
	/**
	 * 주어진 사용자가 작성한 글을 조회함
	 * @return
	 */
	public List<Post> findPostsByWriter(Integer writerSeq) {
		return blogDao.findPostByWriter(writerSeq);
		// writeSeq-1-string
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
	@Transactional
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
		// FIXME 자기가 쓴 글인지 확인해야함
		blogDao.deletePost(pid);
	}

	public List<Post> readPosts(boolean open) {
		return blogDao.readPosts(open);
	}

	public void updateOpen(Integer postSeq, boolean isOpen) {
		blogDao.updateOpen(postSeq,isOpen);
		
	}
	public Post togglePost(Integer seq,User user) {
		// 1. seq 로 글을 가져옴
		Post post = blogDao.findPostBySeq(seq);
		
		// 1.1. 이 글을 쓴 사람하고 지금 로그인한 사람이 같아야 함
		// "==" VS equals()
		// if(post.getWriter().getId().equals(user.getId()))
		if(post.getWriter().getSeq() != user.getSeq())
		{
			throw new RuntimeException("니글 아님");
		}
		// 2.1 글이 공개상태이면 비공개로, 비공개 상태이면 공개로,
		if(post.getOpen()) {
			post.setOpen(false);
		}else {
			post.setOpen(true);
		}
		// 3. dao로 보내서 업데이트해줌
		blogDao.updateOpen(seq,post.getOpen());
		
		return post;
	}
	
	public List<Post> searchPost(Search search) {
		if("writer".equals(search.getSearchType())) {
			User user = userDao.findUserById(search.getKeyword());
			search.setKeyword("" + user.getSeq()); // "204"
		}
		return blogDao.searchPost(search);
		
	}
	
	public List<Post> multiSearchPost(MultiSearch search) {
		// [title, writer] "test2"
		// title like '%204%'
		// writer = 204
		if(search.isWriter()) {
			User user = userDao.findUserById(search.getMultiKeyword());
			if(user != null) {
				search.setWriterKeyword(user.getSeq());
			}
		}
		return blogDao.multiSearchPost(search);
	}
}
