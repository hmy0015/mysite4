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
	public List<BoardVo> getList(String keyword) {
		System.out.println("1. BoardService - 리스트 가져오기");
		
		List<BoardVo> bList = boardDao.getList(keyword);
		
		return bList;
	}
	
	// Service 게시글 등록
	public int write(BoardVo boardVo) {
		System.out.println("1. BoardService - 게시글 등록");
		
		return boardDao.postInsert(boardVo);
	}

	// Service 게시글 읽어오기
	public BoardVo read(int no) {
		System.out.println("1. BoardService - 게시글 읽어오기");
		
		boardDao.cnt(no);
		
		return boardDao.getPost(no);
	}
	
	// Service 게시글 수정
	public int modify(BoardVo boardVo) {
		System.out.println("1. BoardService - 게시글 수정");
		
		return boardDao.update(boardVo);
	}

	// Service 게시글 삭제
	public int delete(int no, String pw) {
		System.out.println("1. BoardService - 게시글 삭제");
		
		return boardDao.delete(no, pw);
	}

}
