package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.ReplyBoardDao;
import com.javaex.vo.PagingVO;
import com.javaex.vo.ReplyBoardVo;

@Service
public class ReplyBoardService {
	@Autowired
	private ReplyBoardDao reboardDao;
	
	// Service 페이징 할 숫자 구하기
	public int postNum(String keyword) {
		
		int postNum = reboardDao.postNum(keyword); // 게시글의 총 개수를 구함
		int page = (int)Math.ceil(postNum/4.0); // => (double -> int 형변환)반올림(총 게시물 개수/출력시킬 게시물 개수)
		
		return page;
	}

	// 리스트 가져오기 및 검색
	public List<ReplyBoardVo> getList(int page, String keyword) {
		System.out.println("1. ReplyBoardService - 리스트 가져오기");

		int startNum = (page * 4) - 3; 
		int endNum = startNum + 3;

		PagingVO listNum = new PagingVO(startNum, endNum, keyword);
		List<ReplyBoardVo> rList = reboardDao.getList(listNum);
		
		return rList;
	}

	// Service 게시글 가져오기
	public ReplyBoardVo read(int no) {
		System.out.println("1. ReplyBoardService - 게시글 가져오기");

		reboardDao.cnt(no);

		return reboardDao.getPost(no);
	}

	// Service 게시글 등록
	public int write(ReplyBoardVo replyboardVo) {
		System.out.println("1. ReplyBoardService - 게시글 등록");

		return reboardDao.postInsert(replyboardVo);
	}

	// Service 답글 등록
	public int reply(ReplyBoardVo replyboardVo) {
		System.out.println("1. ReplyBoardService - 답글 등록");

		reboardDao.oNoUpdate(replyboardVo);

		// 해당글의 order_no, depth를 1씩 증가
		int order_no = replyboardVo.getOrder_no();
		int depth = replyboardVo.getDepth();

		replyboardVo.setOrder_no(order_no + 1);
		replyboardVo.setDepth(depth + 1);

		return reboardDao.replyInsert(replyboardVo);
	}

	// Service 게시글 수정
	public int modify(ReplyBoardVo replyboardVo) {
		System.out.println("1. ReplyBoardService - 게시글 수정");

		return reboardDao.update(replyboardVo);
	}

	// Service 게시글 삭제 방식01 - DB에서 해당 게시글의 데이터를 삭제함
	public int delPost(int no, String pw) {
		System.out.println("1. ReplyBoardService - 게시글 삭제");
		
		return reboardDao.delPost(no, pw);
	}

	// Service 게시글 삭제 02 - 글 제목을 '삭제 된 게시글'로 표시
	public int delete(int no, String pw) {
		System.out.println("1. ReplyBoardService - 게시글 삭제");

		return reboardDao.delete(no, pw);
	}
}
