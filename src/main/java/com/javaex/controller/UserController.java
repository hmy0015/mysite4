package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	// 회원가입 폼
	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("[ joinForm ]");
		
		return "user/joinForm";
	}
	
	// 회원가입 
	@RequestMapping("/join") 
	public String join(@ModelAttribute UserVo userVo){
		System.out.println("1. UserController - join");
		System.out.println(userVo.toString());
		
		userService.join(userVo);
		
		return "user/joinOk";
	}
	
	// 로그인 폼 
	@RequestMapping("/loginForm") 
	public String loginForm(){
		System.out.println("[ loginForm ]");

		return "user/loginForm";
	}
	
	// 로그인 
	@RequestMapping("/login") 
	public String login(@ModelAttribute UserVo userVo, HttpSession session){
		System.out.println("1. UserController - login");

		UserVo authVo = userService.login(userVo);
		
		if(authVo != null) { // 로그인 성공 시
			// 세션에 로그인 한 사용자 정보 담기
			session.setAttribute("authUser", authVo);
			
			return "redirect:/main";
		} 
		else { // 로그인 실패 시
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	// 로그아웃
	@RequestMapping("/logout") 
	public String logout(HttpSession session){
		System.out.println("[ logout ]");
		
		session.removeAttribute("authUser"); // 지정된 이름에 해당하는 객체를 세션에서 제거
		session.invalidate(); // 해당 세션을 없애고 세션에 속해있는 값들을 삭제
		
		return "redirect:/main";
	}
	
	// 회원정보 수정 폼 (로그인 한 사용자 정보 가져오기)
	@RequestMapping("/modifyForm") 
	public String modifyForm(HttpSession session, Model model){
		System.out.println("[ modifyForm ]");

		int no = ((UserVo)session.getAttribute("authUser")).getNo();

		UserVo userVo = userService.getUser(no);
		model.addAttribute("userVo", userVo);
		
		return "user/modifyForm";
	}
	
	// 회원정보 수정
	@RequestMapping("/modify")
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[ modify ]");
		
		// 회원정보 수정
		userService.update(userVo);

		// 수정 된 회원 정보 session에 업데이트
		UserVo authVo = userService.login(userVo);
		session.setAttribute("authUser", authVo);
		
		return "redirect:/main";
	}
}
