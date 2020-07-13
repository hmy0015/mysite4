package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.ReplyBoardVo;

@Repository
public class ReplyBoardDao {
	@Autowired
	private SqlSession sqlSession;

	// dao 리스트 가져오기
	public List<ReplyBoardVo> getList(String keyword) {
		System.out.println("2. ReplyBoardDao - 리스트 가져오기");

		String title = "%" + keyword + "%";
		
		List<ReplyBoardVo> rList = sqlSession.selectList("reboard.getList", title);
		
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

		return sqlSession.insert("reboard.postInsert", replyboardVo);
	}
	
	// dao 답글 등록
	public int replyInsert(ReplyBoardVo replyboardVo) {
		System.out.println("2. ReplyBoardDao - 답글 등록");
		
		return sqlSession.insert("reboard.replyInsert", replyboardVo);
	}
	
	// order_no + 1
	public int oNoUpdate(ReplyBoardVo replyboardVo) {
		System.out.println("2. ReplyBoardDao - order_no + 1");
		
		return sqlSession.update("reboard.oNoUpdate", replyboardVo);
	}
	
	// dao 게시글 수정
	public int update(ReplyBoardVo replyboardVo) {
		System.out.println("2. ReplyBoardDao - 게시글 수정하기");
		
		return sqlSession.update("reboard.update", replyboardVo);
	}
	
	// dao 카운트
	public int cnt(int no) {
		return sqlSession.update("reboard.cnt", no);
	}
	
	// dao 게시글 삭제 방식01 - DB에서 해당 게시글의 데이터를 삭제함
	public int delPost(int no, String pw) {
		System.out.println("2. ReplyBoardDao - 게시글 삭제");
		
		Map<String, Object> delPostMap = new HashMap<String, Object>();
		delPostMap.put("no", no);
		delPostMap.put("password", pw);
		
		return sqlSession.delete("reboard.delPost", delPostMap);
	}
	
	// dao 게시글 삭제 02 - 글 제목을 '삭제 된 게시글'로 표시
	public int delete(int no, String pw) {
		System.out.println("2. ReplyBoardDao - 게시글 삭제");
		
		Map<String, Object> delPostMap = new HashMap<String, Object>();
		delPostMap.put("no", no);
		delPostMap.put("password", pw);
		
		return sqlSession.delete("reboard.delete", delPostMap);
	}
}
