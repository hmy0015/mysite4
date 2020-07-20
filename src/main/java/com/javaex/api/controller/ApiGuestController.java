package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestController {
	@Autowired
	private GuestService guestService;
	
	// 리스트 출력
	@ResponseBody
	@RequestMapping("/list")
	public List<GuestVo> list() {
		System.out.println("[ addList ]");
		
		List<GuestVo> gList = guestService.getList(); // 리스트 가져오기
		System.out.println(gList.toString());
		
		return gList;
	}

	// 방명록 등록
	@ResponseBody
	@RequestMapping("/write")
	public GuestVo write(@RequestBody GuestVo guestVo) {
		System.out.println("[ write ]");
		System.out.println(guestVo.toString());
		
		GuestVo vo = guestService.addGuest(guestVo);
		System.out.println("최종으로 등록 될 게시글의 정보 : " + vo.toString());
		
		return vo;
	}

	// 방명록 삭제
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("[ delete ]");
		System.out.println(guestVo.toString());

		int count = guestService.delete(guestVo);

		return count;
	}
}
