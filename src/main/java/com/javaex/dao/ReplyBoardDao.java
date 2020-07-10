package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.ReplyBoardVo;

@Repository
public class ReplyBoardDao {
	@Autowired
	private SqlSession sqlSession;

	// dao 게시글 가져오기
	public List<ReplyBoardVo> getList() {
		System.out.println("2. ReplyBoardDao - 리스트 가져오기");
		
		List<ReplyBoardVo> rList = sqlSession.selectList("reboard.getList");
		
		System.out.println(rList);
		
		return rList;
	}
}
