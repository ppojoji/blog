package naver.ppojoji.blog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import naver.ppojoji.blog.BlogException;
import naver.ppojoji.blog.dao.BlogDao;
import naver.ppojoji.blog.dao.CategoryDao;
import naver.ppojoji.blog.dao.UserDao;
import naver.ppojoji.blog.dto.Category;
import naver.ppojoji.blog.dto.LocalUpFile;
import naver.ppojoji.blog.dto.MultiSearch;
import naver.ppojoji.blog.dto.Post;
import naver.ppojoji.blog.dto.Search;
import naver.ppojoji.blog.dto.User;

@Service
@Transactional
public class BlogService {
	@Autowired
	BlogDao blogDao;
	
	@Autowired
	CategoryDao cateDao;
	
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
	
	public List<Post> findAllByAdmin(){
		return blogDao.findAllByAdmin();
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
	 * @param i 
	 */
	@Transactional
	public void insertPost(String title, 
			String contents, 
			List<MultipartFile> files,
			Integer cateSeq, // null
			Integer writerSeq) {
			// HttpSession session) {
		Category cate = cateDao.findCategory(cateSeq);
		if (cate == null) {
			if(cateSeq == 0) {
				cateSeq = null;
			} else {
				throw new BlogException(404, "NO_SUCH_CATEGORY");
			}
		}
		Integer postSeq = blogDao.insertPost(title,contents, cateSeq, writerSeq);
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
	 * @param postSeq2 
	 */
	public void updatePost(String title, String contents ,Integer cateSeq ,Integer postSeq ) {
		blogDao.updatePost(title,contents,cateSeq ,postSeq);
	}

	public void deletePost(Integer pid) {
		// FIXME 자기가 쓴 글인지 확인해야함
		/*
		 * 파일을 지우고, 글을 지워야 함
		 */
		Post post = blogDao.findPostBySeq(pid);
		List<LocalUpFile> files = post.getUpFiles();
		for(int i=0;i<files.size();i++) {
			LocalUpFile upfile = files.get(i);
			String upFile = upfile.getGenName();
			fileService.deleteFile(upFile);
			Integer seq = upfile.getSeq();
			blogDao.deleteFile(seq);
		}
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
	
	public List<Post> delYn() {
		List<Post> list =blogDao.delYn();
		return list;
	}
	public void setAsdeleted(Integer userSeq, Integer pid) {
		// pid의 작성자를 가져옴
		Post post = this.readPosts(pid, false);
		
		if(userSeq == post.getWriter().getSeq()) {
			blogDao.setAsdeleted(pid);
		}else {
			throw new BlogException(403, "NOT_A_USER");
		}
		
	}
	public List<Post> findByCateName(String cateName) {
		Category cate = cateDao.findByCateName(cateName);
		return blogDao.findByCate(cate.getSeq());
	}
	public List<Post> findByCate(Integer cateSeq) {
		return blogDao.findByCate(cateSeq);
	}
	
	public List<Map<String,Object>> findRecentNForCates(int n) {
		// 1. 모든 카테고리를 다 가져옴
		List<Category> cates = cateDao.findAllCateList();
		// 2. 각각의 카테고리마다 n개씩 가져옴
		List<Map<String,Object>> overviews = new ArrayList<>();
		for(int i=0; i<cates.size(); i++) {
			Category cate = cates.get(i);
			List<Post> posts = blogDao.findByCate(cate.getSeq()); // 2, 34
			int length = Math.min(n, posts.size());
			List<Post> maxN = posts.subList(0, length);
			Map<String,Object> overview = new HashMap<>();
			overview.put("cate", cate);
			overview.put("posts", maxN);
			overviews.add(overview);
		}
		return overviews;
		
		
	}

}
