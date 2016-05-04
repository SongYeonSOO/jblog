package com.estsoft.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.CategoryDao;
import com.estsoft.jblog.dao.PostDao;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	PostDao pdao;
	@Autowired
	CategoryDao cdao;

	public BlogService() {

	}

	public List<PostVo> SearchPost(CategoryVo cvo) {
		return pdao.SearchPost(cvo);
	}

	// Posting
	public void insert(PostVo cvo) {
		pdao.insert(cvo);
	}

	public List<CategoryVo> SearchCategory() {

		return cdao.SearchCategory();
	}
}
