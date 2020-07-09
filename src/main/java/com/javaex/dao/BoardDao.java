package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	// dao 리스트 가져오기 
	public List<BoardVo> getList() {
		System.out.println("2. BoardDao - 리스트 가져오기");
		
		List<BoardVo> bList = sqlSession.selectList("board.getList");
		
		return bList;
	}
	
	// dao 글쓰기
	public int postInsert(BoardVo boardVo) {
		System.out.println("2. BoardDao - 게시글 등록");
		
		return sqlSession.insert("board.insert", boardVo);
	}
	
	// dao 수정
}
