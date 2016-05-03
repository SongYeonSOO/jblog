package com.estsoft.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		//login service 호출(login 작업)
		UserVo authUser = userService.login(id,passwd);

		if(authUser == null){
			//request.getContextPath() ; -> /jblog까지임 
			response.sendRedirect(request.getContextPath()+"/user/loginform");
		
			//의미상 끝났지만 그래도 false를 return 써주시오
			return false;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath()+"/main");
		return false;
		
	}
}
