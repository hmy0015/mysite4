package com.javaex.dao;

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
		System.out.println("1. Dao 파일 업로드");
		//System.out.println("Dao에서의 이미지 정보 : " + galleryVo.toString());
		
		return sqlSession.insert("gallery.insert", galleryVo);
	}
}
