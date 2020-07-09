package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	// Service 리스트 가져오기
	public List<BoardVo> getList() {
		System.out.println("1. BoardService - 리스트 가져오기");
		
		return boardDao.getList();
	}
	
}
