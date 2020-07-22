package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService gService;

	@RequestMapping("/list")
	public String gallery() {
		System.out.println("[ list ]");
		
		return "gallery/list";
	}

	// 이미지 업로드
	@RequestMapping("/imageUpload")
	public String imageUpload ( @RequestParam("image") MultipartFile image,
								@RequestParam("content") String content,
								@RequestParam("uNo") int uNo) {
		System.out.println("[ imageUpload ]");
		
		String orgName = image.getOriginalFilename();
		GalleryVo galleryVo = new GalleryVo(uNo, content, orgName);
		
		System.out.println(galleryVo.toString());
		
		gService.imageUpload(galleryVo, image);
		
		return "redirect:/gallery/list";
	}
}
