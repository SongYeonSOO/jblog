package com.estsoft.jblog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;

	public CategoryDao() {

	}

	// join시 blog 삽입
	public void insert(BlogVo vo) {
		CategoryVo cvo = new CategoryVo();
		cvo.setNo(vo.getNo());
		cvo.setName("미분류");
		cvo.setDescription("카테고리를 지정하지 않은 경우");
		sqlSession.insert("category.insert", cvo);

	}

	public List<CategoryVo> SearchCategory(String id) {
		return sqlSession.selectList("category.getlist", id);
	}

	public void insertCate(CategoryVo cvo) {
		sqlSession.insert("category.insert", cvo);

	}

	public void deleteCate(Long category_no) {
		sqlSession.delete("category.delete", category_no);
	}

	public void countUpdate(Long category_no) {

		sqlSession.delete("category.countUpdate", category_no);
	}
}
