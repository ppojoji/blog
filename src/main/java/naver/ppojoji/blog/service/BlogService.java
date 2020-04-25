package naver.ppojoji.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import naver.ppojoji.blog.dao.BlogDao;
import naver.ppojoji.blog.dao.FileDao;
import naver.ppojoji.blog.dto.Post;

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
	*/
	public List<Post> findAllPosts() {
		return blogDao.findAllPost();
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
	public void insertPost(String title, String contents, List<MultipartFile> files) {
		Integer postSeq = blogDao.insertPost(title,contents);
//		Integer postSeq = -1;
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
}
