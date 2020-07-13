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

	// dao 리스트 가져오기
	public List<ReplyBoardVo> getList() {
		System.out.println("2. ReplyBoardDao - 리스트 가져오기");
		
		List<ReplyBoardVo> rList = sqlSession.selectList("reboard.getList");
		
		System.out.println(rList);
		
		return rList;
	}

	// dao 게시글 가져오기
	public ReplyBoardVo getPost(int no) {
		System.out.println("2. ReplyBoardDao - 게시글 가져오기");
		
		return sqlSession.selectOne("reboard.getPost", no);
	}
	
	// dao 게시글 등록
	public int postInsert(ReplyBoardVo replyboardVo) {
		System.out.println("2. ReplyBoardDao - 게시글 등록");

		System.out.println(replyboardVo.toString());
		
		return sqlSession.insert("reboard.postInsert", replyboardVo);
	}
	
	// dao 답글 등록
	public int replyInsert(ReplyBoardVo replyboardVo) {
		System.out.println("2. ReplyBoardDao - 답글 등록");
		
		int order_no = replyboardVo.getOrder_no();
		int depth = replyboardVo.getDepth();

		replyboardVo.setOrder_no(order_no + 1);
		replyboardVo.setDepth(depth + 1);

		System.out.println(replyboardVo.toString());
		
		return sqlSession.insert("reboard.replyInsert", replyboardVo);
	}
	
	// order_no + 1
	public int oNoUpdate(int group_no) {
		System.out.println("2. ReplyBoardDao - order_no + 1");
		
		return sqlSession.update("reboard.oNoUpdate", group_no);
	}
}
