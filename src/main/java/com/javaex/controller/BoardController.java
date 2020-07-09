package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	// 리스트
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("[ list ]");
		
		List<BoardVo> boardVo = boardService.getList();
		model.addAttribute("bList", boardVo);
		
		return "board/list";
	}
	
	// 게시글 등록 폼
	@RequestMapping("/writeForm")
	public String writeForm() {
		System.out.println("[ writeForm ]");
		
		return "board/writeForm";
	}
	
	// 게시글 등록
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("[ write ]");

		boardService.write(boardVo);
		
		return "redirect:/board/list";
	}
	
	// 게시글 읽어오기
	@RequestMapping("/read/{no}")
	public String read(@PathVariable("no") int no, Model model) {
		System.out.println("[ read ]");
		
		BoardVo boardVo = boardService.read(no);
		model.addAttribute("vo", boardVo);
		
		return "board/read";
	}
	
	// 게시글 수정 폼
	@RequestMapping("/modifyForm/{no}")
	public String modifyForm(@PathVariable("no") int no, Model model) {
		System.out.println("[ modifyForm ]");
		
		BoardVo boardVo = boardService.read(no);
		model.addAttribute("vo", boardVo);
		
		return "board/modifyForm";
	}
	
	// 게시글 수정
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("[ modify ]");
		
		boardService.modify(boardVo);
		
		return "redirect:/board/list";
	}
	
	// 게시글 삭제
}
