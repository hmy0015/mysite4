package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@RequestMapping("/list")
	public String list() {
		System.out.println("[ list ]");
		
		return "board/list";
	}
	
}
