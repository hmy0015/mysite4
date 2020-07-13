package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	// 게시글 등록 폼
	@RequestMapping("/writeForm/{uNo}")
	public String writeForm(@PathVariable("uNo") int uNo, Model model) {
		System.out.println("[ ReplyBoard - writeForm ]");
		
		model.addAttribute("uNo", uNo);
		
		return "reboard/writeForm";
	}
	
	// 게시글 등록
	@RequestMapping("/write")
	public String write(@ModelAttribute ReplyBoardVo reboardVo) {
		System.out.println("[ ReplyBoard - write ]");
		
		reboardService.write(reboardVo);
		
		return "redirect:/reboard/list";
	}
	
	// 답글 등록 폼
	@RequestMapping("/replyForm/{no}")
	public String replyForm(@PathVariable("no") int no, Model model) {
		System.out.println("[ ReplyBoard - replyForm ]");		
		
		// 해당 게시글의 gNo, oNo, depth를 알기 위해 해당 게시글의 정보 받아옴
		ReplyBoardVo reboardVo = reboardService.read(no);
		model.addAttribute("vo", reboardVo); // 게시글 정보 담기
		
		return "reboard/replyForm";
	}
	
	// 답글 등록
	@RequestMapping("/reply")
	public String reply(@ModelAttribute ReplyBoardVo reboardVo) {
		System.out.println("[ ReplyBoard - reply ]");
		
		System.out.println(reboardVo.toString());
		reboardService.reply(reboardVo);
		
		return "redirect:/reboard/list";
	}
}
