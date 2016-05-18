package com.estsoft.jblog.controller;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.jblog.service.BlogService;

@Controller
@RequestMapping("/main")
public class MainController {
	// LOG 찍자!!!!!
	private static final Log LOG = LogFactory.getLog(MainController.class);

	@RequestMapping("")
	public String index(@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd,
			@RequestParam(value = "page", required = true, defaultValue = "1") Long page,Model model) {
		// index method 이용시 debug log를 남김
		LOG.debug("index called ");
		BlogService blogService = new BlogService();
		
		Map<String, Object> map = blogService.SearchList(kwd, page);
		
		model.addAttribute("pageinfo", map.get("pageinfo"));
		model.addAttribute("blogno",map.get("blogno"));
		model.addAttribute("list",map.get("list"));
		return "main/index";
	}
}
