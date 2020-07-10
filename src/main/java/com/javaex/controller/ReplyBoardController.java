package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.ReplyBoardService;
import com.javaex.vo.ReplyBoardVo;

@Controller
@RequestMapping("/reboard")
public class ReplyBoardController {
	@Autowired
	private ReplyBoardService reboardService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("[ ReplyBoard - list ]");
		
		List<ReplyBoardVo> rList = reboardService.getList();
		
		model.addAttribute("rList", rList);
		
		return "reboard/list";
	}
}
