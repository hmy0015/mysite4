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

	// dao 게시글 가져오기 및 검색
	public List<BoardVo> getList(String keyword) {
		
		String title = "%" + keyword + "%";
		
		List<BoardVo> bList = sqlSession.selectList("board.getList", title);
		
		return bList;
	}
	
	// dao 게시글 등록
	public int postInsert(BoardVo boardVo) {
		System.out.println("2. BoardDao - 게시글 등록");
		
		return sqlSession.insert("board.insert", boardVo);
	}
	
	// dao 게시글 읽어오기
	public BoardVo getPost(int no) {
		System.out.println("2. BoardDao - 게시글 읽어오기");
		
		return sqlSession.selectOne("board.getPost", no);
	}
	
	// dao 게시글 수정
	public int update(BoardVo boardVo) {
		System.out.println("2. BoardDao - 게시글 수정하기");
		
		return sqlSession.update("board.update", boardVo);
	}

	// dao 게시글 삭제
	public int delete(int no) {
		System.out.println("2. BoardDao - 게시글 삭제");
		
		return sqlSession.delete("board.delete", no);
	}
	
	// dao 카운트
	public int cnt(int no) {
		return sqlSession.update("board.cnt", no);
	}
	
}
