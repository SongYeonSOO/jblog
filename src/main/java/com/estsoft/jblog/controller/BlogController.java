package com.estsoft.jblog.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.jblog.annotation.Auth;
import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.PostVo;


@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogService postService;

	@RequestMapping( {"{blogId}","${blogId}/{category_no}"})
	public String MyBlog(@PathVariable(value = "category_no") Long category_no,@PathVariable(value = "blogId") String blogId,
			Model model) {
		if(category_no == null){
			category_no = 1L;
		}
		System.out.println(category_no);
		//category list
		List<CategoryVo> clist = blogService.SearchCategory();
		model.addAttribute("category", clist);
		
		//post list
		CategoryVo cvo = new CategoryVo();
		cvo.setCategory_no(category_no);
		List<PostVo> plist = blogService.SearchPost(cvo);
		model.addAttribute("plist", plist);
		
		
		return "blog/blog-main";

	}

	@RequestMapping("/blog-admin-category")
	public String Category() {

		return "blog/blog-admin-category";

	}

	@RequestMapping("/blog-admin-write")
	public String Write(@ModelAttribute PostVo vo, Model model) {

		List<CategoryVo> clist = blogService.SearchCategory();

		model.addAttribute("category", clist);
		model.addAttribute("vo", vo);
		return "blog/blog-admin-write";
	}

	@Auth
	@RequestMapping("/blog-admin-writing")
	// @AuthUser로 받는 parameter는 반드시 인증된 사용자가 넘어오게된다
	public String write(@ModelAttribute PostVo vo, @RequestParam(value = "category") Long cvono) {
		System.out.println(cvono);
		vo.setCategory_no(cvono);
		postService.insert(vo);

		return "redirect:/blog";
	}

	// blog관리
	@RequestMapping("/blog-admin-basic")
	public String Basic() {

		return "blog/blog-admin-basic";

	}
}
