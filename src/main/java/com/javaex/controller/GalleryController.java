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
	public String imageUpload ( @RequestParam("image") MultipartFile image, // file 형태를 파라미터로 받아오기 위해서는 [ MultipartFile ] 클래스가 필요함 (오류날 경우 import 해 볼 것)
								@RequestParam("content") String content, // @RequestParam("파라미터명") 자료형 + 변수명
								@RequestParam("uNo") int uNo) {
		
		System.out.println("[ imageUpload ]");

		String orgName = image.getOriginalFilename(); // .getOriginalFilename() => 해당 파일의 이름을 가져옴
		GalleryVo galleryVo = new GalleryVo(uNo, content, orgName); // vo에 담아줌 (vo에 해당 파라미터 개수와 일치하는 생성자가 있어야 함)

		System.out.println(galleryVo.toString()); // vo에 uNo, content, orgName 값이 잘 담겼는지 확인

		gService.imageUpload(galleryVo, image); // 서비스 클래스에 vo와 image를 담아서 메소드 호출해 줌

		return "redirect:/gallery/list";
	}

	// 이미지 삭제
	@ResponseBody // return으로 [url]이 아니라 [값]을 반환하기 위해서  삽입해줘야 함 / 외워둘 것
	@RequestMapping("/delete")
	public int delete(@RequestParam("no") int no) {

		int cnt = gService.delete(no); // DB에서 해당 게시글이 삭제 되면 삭제 된 게시글의 개수가 리턴됨 (현재로서는 1개의 게시물만 삭제되기 때문에 1이 반환됨)
		
		// 값! 반환
		return cnt;
	}
	
	// 게시글 정보 가져오기
	@ResponseBody
	@RequestMapping("/getPostInfo")
	public GalleryVo getPostInfo(@RequestParam("no") int no) {

		GalleryVo vo = gService.getPostInfo(no); // 이미지 출력을 위해 db에 저장 된 해당 게시물의 정보 받아옴 (user_no, content, saveName)

		return vo;
	}
	
}
