package com.estsoft.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.BlogDao;
import com.estsoft.jblog.dao.CategoryDao;
import com.estsoft.jblog.dao.UserDao;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.UserVo;

@Service
public class UserService {
	public UserService() {

	}

	@Autowired
	UserDao udao;
	@Autowired
	BlogDao bdao;
	@Autowired
	CategoryDao cdao;

	public void join(UserVo vo) {
		udao.insert(vo);
		bdao.insert(vo);
		BlogVo bvo = bdao.getOneBlog(vo);
		cdao.insert(bvo);
	}

	public UserVo getUser(String id) {

		UserVo vo = udao.getUser(id);

		return vo;
	}

	public UserVo login(String id, String passwd) {

		UserVo vo = udao.get(id, passwd);

		return vo;
	}
}
