package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.ReplyBoardService;
import com.javaex.vo.ReplyBoardVo;

@Controller
@RequestMapping("/reboard")
public class ReplyBoardController {
	@Autowired
	private ReplyBoardService reboardService;
	
	// 리스트
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("[ ReplyBoard - list ]");
		
		List<ReplyBoardVo> rList = reboardService.getList();
		
		model.addAttribute("rList", rList);
		
		return "reboard/list";
	}
	
	// 게시글 가져오기
	@RequestMapping("/read/{no}")
	public String read(@PathVariable("no") int no, Model model) {
		System.out.println("[ ReplyBoard - read ]");
		
		ReplyBoardVo reboardVo = reboardService.read(no);
		model.addAttribute("vo", reboardVo);
		
		return "reboard/read";
	}
	
	// 게시글 등록
	@RequestMapping("/writeForm")
	public String writeForm(@PathVariable("uNo") int uNo, Model model) {
		System.out.println("[ ReplyBoard - writeForm ]");
		
		model.addAttribute("uNo", uNo);
		
		return "reboard/writeForm";
	}
}
