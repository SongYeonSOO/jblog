package com.estsoft.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estsoft.jblog.service.BlogService;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;

	@RequestMapping("")
	public String MyBlog() {

		return "blog/blog-main";

	}
	

	@RequestMapping("/blog-admin-category")
	public String Category() {

		return "blog/blog-admin-category";

	}
	@RequestMapping("/blog-admin-write")
	public String Write() {

		return "blog/blog-admin-write";

	}
	//blog관리
	@RequestMapping("/blog-admin-basic")
	public String Basic() {

		return "blog/blog-admin-basic";

	}
}
