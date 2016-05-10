package com.estsoft.jblog.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;

	public UserDao() {
	}

	public void insert(UserVo vo) {

		int count = sqlSession.insert("user.insert", vo);

	}

	public UserVo getUser(String id) {
		UserVo vo = sqlSession.selectOne("user.selectUser", id);
		return vo;
	}

	// secutiry : authentication + permission(root mode etc...)
	// authentication(합당한 사람이냐!)
	public UserVo get(String id, String passwd) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("passwd", passwd);

		UserVo vo = sqlSession.selectOne("user.selectAuthUser", map);
		return vo;

	}

}
