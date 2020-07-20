package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestController {
	@Autowired
	private GuestService guestService;
	
	@ResponseBody
	@RequestMapping("/list")
	public List<GuestVo> list() {
		System.out.println("[ addList ]");
		
		List<GuestVo> gList = guestService.getList(); // 리스트 가져오기
		System.out.println(gList.toString());
		
		return gList;
	}
}
