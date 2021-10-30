package naver.ppojoji.blog.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.ppojoji.blog.BlogException;
import naver.ppojoji.blog.BoardType;
import naver.ppojoji.blog.Error;
import naver.ppojoji.blog.YesNo;
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
	public Category updateCate(Integer cateSeq, String prop ,  Object value) {
		if(value == null) {
			throw new BlogException(400, Error.EMPTY_CATE_NAME);
		}
		
		Category cate = cateDao.findCategory(cateSeq);
		if ("name".equals(prop)) {
			String cateName = (String) value; // 
			Category existing = cateDao.findByCateName(cateName);
			if(existing != null) {
				throw new BlogException(409, Error.DUP_CATE_NAME);
				
			}
			cate.setName(value.toString());
		} else if ("useYn".equals(prop)) {
			String v = (String) value; // 강제로 캐스팅 "Y", "N"
			YesNo yn = YesNo.valueOf(v); // "Y" -> YesNo.Y, "N" -> YesNo.N, "V"
			cate.setUseYn(yn);
		} else if("replyYN".equals(prop)){
			String v = (String)value; 
			YesNo yn = YesNo.valueOf(v);
			cate.setReplyYN(yn);
		}else if("type".equals(prop)) {
			String v = (String)value; 
			BoardType type = BoardType.valueOf(v);
			cate.setType(type);
		}
		else {
			throw new BlogException(400, Error.BAD_REQUEST);
		}
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
