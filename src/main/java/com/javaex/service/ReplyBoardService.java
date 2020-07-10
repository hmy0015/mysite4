package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.ReplyBoardDao;
import com.javaex.vo.ReplyBoardVo;

@Service
public class ReplyBoardService {
	@Autowired
	private ReplyBoardDao reboardDao;

	// Service 리스트 가져오기
	public List<ReplyBoardVo> getList() {
		System.out.println("1. ReplyBoardService - 리스트 가져오기");
		
		List<ReplyBoardVo> rList = reboardDao.getList();
		
		return rList;
	}

	// Service 게시글 가져오기
	public ReplyBoardVo read(int no) {
		System.out.println("1. ReplyBoardService - 게시글 가져오기");
		
		return reboardDao.getPost(no);
	}
}
