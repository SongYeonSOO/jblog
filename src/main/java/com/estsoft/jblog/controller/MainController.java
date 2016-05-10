package com.estsoft.jblog.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {
	// LOG 찍자!!!!!
	private static final Log LOG = LogFactory.getLog(MainController.class);

	@RequestMapping("")
	public String index() {
		// index method 이용시 debug log를 남김
		LOG.debug("index called ");
		return "main/index";
	}
}
