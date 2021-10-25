package naver.ppojoji.blog.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.ppojoji.blog.BlogException;
import naver.ppojoji.blog.Error;
import naver.ppojoji.blog.dao.CategoryDao;
import naver.ppojoji.blog.dto.Category;
import naver.ppojoji.blog.dto.Post;

@Service
@Transactional
public class CategoryService {
	@Autowired
	CategoryDao cateDao;
	@Autowired
	BlogService blogService;
	
	public List<Category> findAllCate() {
		return cateDao.findAllCateList();
	}
	public void deleteCate(Integer cateSeq) {
		List<Post> posts = blogService.findByCate(cateSeq);
		if(posts.size() == 0) {
			cateDao.deleteCate(cateSeq);
		}else {
			throw new BlogException(409, Error.NOT_EMPTY_CATE);
		}
	}
	public List<Category> sameNameCate(String name) {
		return cateDao.sameNameCate(name);
	}
	public Category insertCate(String cateName) {
		Category cate = cateDao.findByCateName(cateName);
		if(cate != null) {
			throw new BlogException(409, Error.DUP_CATE_NAME);
		}
		Integer maxOrderNum = cateDao.maxOrder() + 1;
		return cateDao.insertCate(cateName,maxOrderNum);
	}
	public Category updateCate(Integer cateSeq, String cateName) {
		if(cateName == null || cateName.trim().length() == 0) {
			throw new BlogException(400, Error.EMPTY_CATE_NAME);
		}
		cateName = cateName.trim(); // 
		Category existing = cateDao.findByCateName(cateName);
		
		if(existing != null) {
			throw new BlogException(409, "DUP_CATE_NAME");
		}
		Category cate = cateDao.findCategory(cateSeq);
		cate.setName(cateName);
		return cateDao.updateCate(cate);
	}
	public void orderChange(Integer srcCateSeq, Integer dstCateSeq) {
		Category srcCate = cateDao.findCategory(srcCateSeq);
		Category dstCate = cateDao.findCategory(dstCateSeq);
		
		if(srcCate == null || dstCate == null) {
			throw new BlogException(404, Error.NOT_FOUND);
		}
		// 
		Integer tmp = srcCate.getOrdernum();
		srcCate.setOrdernum(dstCate.getOrdernum());
		dstCate.setOrdernum(tmp);
		
		cateDao.updateCate(srcCate);
		cateDao.updateCate(dstCate);
		
	}

}
