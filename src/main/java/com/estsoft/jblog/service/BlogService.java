package com.estsoft.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.BlogDao;
import com.estsoft.jblog.dao.CategoryDao;
import com.estsoft.jblog.dao.PostDao;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.PostVo;
import com.estsoft.jblog.vo.UserVo;

@Service
public class BlogService {
	
	@Autowired
	BlogDao bdao;
	@Autowired
	PostDao pdao;
	@Autowired
	CategoryDao cdao;

	public BlogService() {

	}
	public BlogVo Blog(String id) {
		UserVo vo = new UserVo();
		vo.setId(id);
		
		return bdao.getOneBlog(vo);
	}
	public List<PostVo> SearchPost(CategoryVo cvo) {
		return pdao.SearchPost(cvo);
	}

	// Posting
	public void insert(PostVo pvo) {
		pdao.insert(pvo);
	}

	public List<CategoryVo> SearchCategory(String blogId) {

		return cdao.SearchCategory(blogId);
	}
	
	public void insertCate(CategoryVo cvo) {
		cdao.insertCate(cvo);

	}
	public void deleteCate(Long category_no){
		cdao.deleteCate(category_no);
	}
	public void countUpdate(Long category_no) {
		cdao.countUpdate(category_no);
		
	}
	public void UpdateBlog(BlogVo bvo) {
		bdao.UpdateBlog(bvo);
		
	}
}
