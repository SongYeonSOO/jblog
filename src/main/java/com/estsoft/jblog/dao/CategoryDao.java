package com.estsoft.jblog.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.UserVo;

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
		cvo.setPost_count(0L);
		int count = sqlSession.insert("category.insert", cvo);
		// 원래 return no해야함 지금은 test용
		// return vo.getNo();

	}

	public List<CategoryVo> SearchCategory() {
		return sqlSession.selectList("category.getlist");
	}
}