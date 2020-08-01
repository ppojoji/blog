package naver.ppojoji.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.ppojoji.blog.dao.CategoryDao;
import naver.ppojoji.blog.dto.Category;

@Service
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

}
