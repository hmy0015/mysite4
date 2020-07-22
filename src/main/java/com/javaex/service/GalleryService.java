package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	@Autowired
	private GalleryDao gDao;
	
	// service 이미지 업로드
	public int imageUpload(GalleryVo galleryVo, MultipartFile image) {
		System.out.println("1. service 파일 업로드");

		//////////////// 데이터 추출 ////////////////
		// 파일이 저장 될 하드의 위치
		String saveDir = "C:\\javaStudy\\upload";
		
		// 사용자가 업로드 한 파일의 이름 (orgName)
		String orgName = image.getOriginalFilename();
		System.out.println("파일명 : " + orgName);
		
		// 해당 파일의 확장자 추출 (exName)
		String exName = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
		System.out.println("확장자 : " + exName);

		// 업로드 된 파일의 사이즈 (fileSize)
		long fileSize = image.getSize();
		System.out.println("파일 사이즈 : " + fileSize);
		
		// 하드에 저장 될 파일의 이름 (saveName)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("저장명 : " + saveName);

		// 하드에 저장 된 파일의 경로 (filePath)
		String filePath = saveDir + "//" + saveName;
		
		//////////////// 추출한 데이터 서버에 복사 ////////////////
		try {
			
			byte[] fileData =  image.getBytes(); // 저장할 파일을 'fileData' 변수에 담기
			OutputStream out = new FileOutputStream(filePath); // 하드에 사진을 저장하기 위해서 아웃풋스트림이 사용됨
			BufferedOutputStream bOut = new BufferedOutputStream(out); // 버퍼에 담기 (속도 향상)
			
			bOut.write(fileData);
			bOut.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 이미지 정보 vo에 담기
		GalleryVo vo = new GalleryVo ( galleryVo.getUser_no(), galleryVo.getContent(),
										filePath, orgName, saveName, fileSize);
	
		return gDao.insert(vo);
	}

	// service 이미지 리스트
	public List<GalleryVo> getList() {
		System.out.println("1. service 파일 리스트");
		
		List<GalleryVo> imageList = gDao.getList();
		
		return imageList;
	}
}
