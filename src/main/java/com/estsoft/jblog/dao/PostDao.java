package com.estsoft.jblog.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.PostVo;

@Repository
public class PostDao {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;

	public PostDao() {

	}

	// posting된 글을 남기자
	public List<PostVo> SearchPost(CategoryVo cvo) {
		List<PostVo> list = sqlSession.selectList("post.searchPost", cvo);
		return list;

	}

	public void insert(PostVo pvo) {
		sqlSession.insert("post.insert", pvo);

	}
}
