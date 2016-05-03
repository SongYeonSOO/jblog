package com.estsoft.jblog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.jblog.service.UserService;
import com.estsoft.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/join")
	public String joinform() {

		return "user/join";

	}

	@RequestMapping(value = "/joinning", method = RequestMethod.POST)
	public String join(@Valid @ModelAttribute UserVo vo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());

			return "/user/join";
		}
		userService.join(vo);

		return "redirect:/user/joinsuccess";

	}

	// success시 /user/joinsuccess.jsp로 넘어가자! (forwarding)
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {

		return "user/joinsuccess";
	}

	@RequestMapping("/loginform")
	public String login() {
		return "user/login";
	}
	

	@RequestMapping("/checkid")
	@ResponseBody
	public Map<String, Object> checkId(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		UserVo vo = userService.getUser(id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", vo == null);

		// do not use JSONObject!!!! json string을 return하기 위해서 그냥 객체를 return 함
		return map;
	}

}
