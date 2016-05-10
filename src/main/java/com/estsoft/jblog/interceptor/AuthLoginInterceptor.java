package com.estsoft.jblog.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.jblog.vo.UserVo;

import com.estsoft.jblog.service.UserService;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	UserService userService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		// login service 호출(login 작업)
		UserVo authUser = userService.login(id, passwd);
		JSONObject json = new JSONObject();

		if (authUser == null) {

			json.put("result", "success");
			json.put("data", false);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(json.toString());

			// 의미상 끝났지만 그래도 false를 return 써주시오
			return false;
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);

		json.put("result", "success");
		json.put("data", true);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(json.toString());

		return false;

	}
}
