package naver.ppojoji.blog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.ppojoji.blog.dto.Category;

@Repository
public class CategoryDao {
	@Autowired
	SqlSession session; 
	
	public Category findCategory(Integer seq) {
		// null -> int (NullpointerException)
		return session.selectOne("CategoryMapper.category",seq);
	}

	public List<Category> findAllCateList() {
		return session.selectList("CategoryMapper.cateList");
	}

	public void deleteCate(Integer seq) {
		session.delete("CategoryMapper.deleteCate",seq);
	}
	
	public List<Category> sameNameCate(String name) {
		return session.selectList("CategoryMapper.sameNameCate",name);
	}

	public Category insertCate(String cateName) {
		Category cate = new Category();
		cate.setName(cateName);
		session.insert("CategoryMapper.insertCate",cate);
		return cate; // 일단 null로...
	}
	
	public Category findByCateName(String cateName) {
		return session.selectOne("CategoryMapper.findByCateName",cateName);
	}
}
