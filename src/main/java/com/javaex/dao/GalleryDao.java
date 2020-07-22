package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	@Autowired
	private SqlSession sqlSession;
	
	// dao 이미지 업로드
	public int insert(GalleryVo galleryVo) {
		System.out.println("2. Dao 파일 업로드");
		//System.out.println("Dao에서의 이미지 정보 : " + galleryVo.toString());
		
		return sqlSession.insert("gallery.insert", galleryVo);
	}
	
	// dao 이미지 리스트
	public List<GalleryVo> getList() {
		System.out.println("2. Dao 파일 리스트");
		
		List<GalleryVo> imageList = sqlSession.selectList("gallery.getList");
		
		return imageList;
	}
}
