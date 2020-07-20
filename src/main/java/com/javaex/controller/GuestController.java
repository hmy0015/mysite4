package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("/guest")
public class GuestController {
	@Autowired
	private GuestService guestService;

	// 리스트 가져오기
	@RequestMapping("/addList")
	public String addList(Model model) {
		System.out.println("[ addList ]");
		
		List<GuestVo> gList = guestService.getList(); // 리스트 가져오기
		model.addAttribute("gList", gList); // 리스트 담기
		
		return "guestbook/addList";
	}
	
	// 방명록 추가
	@RequestMapping("/write")
	public String write(@ModelAttribute GuestVo guestVo) {
		System.out.println("[ write ]");
		
		guestService.write(guestVo);
		
		return "redirect:/guest/addList";
	}
	
	// 방명록 삭제 폼
	@RequestMapping("/deleteForm/{no}")
	public String deleteForm(@PathVariable("no") int no, Model model) {
		System.out.println("[ deleteForm ]");
		
		model.addAttribute("no", no);
		
		return "guestbook/deleteForm";
	}
	
	// 방명록 삭제
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("[ delete ]");
		
		guestService.delete(guestVo);
		
		return "redirect:/guest/addList";
	}

	// ajax 방명록
	@RequestMapping("/ajaxList")
	public String ajaxList() {
		System.out.println("[ ajaxList ]");
		
		return "guestbook/ajaxList";
	}
}
