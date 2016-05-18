package com.estsoft.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public void deleteCate(Long category_no) {
		cdao.deleteCate(category_no);
	}

	public void countUpdate(Long category_no) {
		cdao.countUpdate(category_no);

	}

	public void countdownUpdate(Long category_no) {
		cdao.countDownUpdate(category_no);
		
	}
	
	public void UpdateBlog(BlogVo bvo) {
		bdao.UpdateBlog(bvo);

	}

	public PostVo SearchOnePost(Long post_no) {
		PostVo pvo = pdao.SearchOnePost(post_no);
		return pvo;
	}

	public void deletePost(Long post_no) {
		pdao.deletePost(post_no);
		
	}

	public Map<String, Object> SearchList(String kwd, Long page) {
		
		//page에 이용!!!
		 int COUNT_LIST = 5;
		 int COUNT_PAGE = 5;
		Long currentpage = page;
		Long beginpage = currentpage - ((currentpage-1)%COUNT_PAGE);
		Long totalpage = (long) Math.ceil(bdao.Count(kwd)/(float)COUNT_PAGE);
		Long maxpage = null;
		if(totalpage>=beginpage+COUNT_PAGE-1){
			maxpage = beginpage+COUNT_PAGE-1;
		}else{
			maxpage = totalpage;
		}
		
		Long blognum = bdao.Count(kwd)-(currentpage-1)*COUNT_PAGE;
		System.out.println("1231231231" + blognum);
		
		Map<String, Long> pageinfo = new HashMap<String, Long>();
		pageinfo.put("beginpage", beginpage);
		pageinfo.put("totalpage", totalpage);
		pageinfo.put("maxpage", maxpage);
		pageinfo.put("currentpage", currentpage);		
		
		List<BlogVo> list = bdao.SearchList(kwd, page);;
		
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageinfo", pageinfo);
		map.put("blognum", blognum);
		map.put("list", list);
		return map;
		}

}
