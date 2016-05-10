package com.estsoft.jblog.dao;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.UserVo;

@Repository
public class BlogDao {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;

	public BlogDao() {

	}

	// join시 blog 삽입
	public void insert(UserVo vo) {
		BlogVo bvo = new BlogVo();
		bvo.setLogo("modify your logo");
		bvo.setTitle("modify your blog title");
		bvo.setId(vo.getId());
		sqlSession.insert("blog.insert", bvo);

		// 원래 return no해야함 지금은 test용
		// return vo.getNo();

	}

	public BlogVo getOneBlog(UserVo vo) {
		BlogVo bvo = sqlSession.selectOne("blog.getoneblog", vo.getId());
		return bvo;
	}

	public void UpdateBlog(BlogVo bvo) {

		sqlSession.update("blog.updateblog", bvo);
	}

}
