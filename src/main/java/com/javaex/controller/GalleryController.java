package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService gService;

	// 갤러리 리스트
	@RequestMapping("/list")
	public String gallery(Model model) {
		System.out.println("[ list ]");

		List<GalleryVo> imageList = gService.getList();
		model.addAttribute("iList", imageList);

		return "gallery/list";
	}

	// 이미지 업로드
	@RequestMapping("/imageUpload")
	public String imageUpload(@RequestParam("image") MultipartFile image, @RequestParam("content") String content,
			@RequestParam("uNo") int uNo) {
		System.out.println("[ imageUpload ]");

		String orgName = image.getOriginalFilename();
		GalleryVo galleryVo = new GalleryVo(uNo, content, orgName);

		System.out.println(galleryVo.toString());

		gService.imageUpload(galleryVo, image);

		return "redirect:/gallery/list";
	}

	// 이미지 삭제
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(@RequestParam("no") int no) {

		int cnt = gService.delete(no);

		return cnt;
	}
	
	// 게시글 정보 가져오기
	@ResponseBody
	@RequestMapping("/getPostInfo")
	public GalleryVo getPostInfo(@RequestParam("no") int no) {

		GalleryVo vo = gService.getPostInfo(no);

		return vo;
	}
	
}