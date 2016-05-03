package com.estsoft.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.UserDao;
import com.estsoft.jblog.vo.UserVo;

@Service
public class UserService {
	public UserService(){
		
	}
	@Autowired
	UserDao dao;
	public void join(UserVo vo){
		dao.insert(vo);
	}
	
	
	public UserVo getUser(String id){
		
		UserVo vo = dao.getUser(id);
		
	return vo;
	}
	
	public UserVo login(String id,String passwd){
		
		UserVo vo = dao.get(id, passwd);
		
	return vo;
	}
}
