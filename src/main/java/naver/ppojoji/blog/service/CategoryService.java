package naver.ppojoji.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.ppojoji.blog.BlogException;
import naver.ppojoji.blog.dao.CategoryDao;
import naver.ppojoji.blog.dto.Category;

@Service
@Transactional
public class CategoryService {
	@Autowired
	CategoryDao cateDao;
	public List<Category> findAllCate() {
		return cateDao.findAllCateList();
	}
	public void deleteCate(Integer seq) {
		cateDao.deleteCate(seq);
	}
	public List<Category> sameNameCate(String name) {
		return cateDao.sameNameCate(name);
	}
	public Category insertCate(String cateName) {
		Category cate = cateDao.findByCateName(cateName);
		if(cate != null) {
			throw new BlogException(409, "DUP_CATE_NAME");
		}
		return cateDao.insertCate(cateName);
	}

}
