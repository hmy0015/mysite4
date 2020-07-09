package com.javaex.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	@Autowired
	private SqlSession sqlSession;

	// dao 방명록 리스트 가져오기
	public List<GuestVo> getList() {
		System.out.println("2. GuestDao - 리스트 가져오기");
		
		List<GuestVo> gList = sqlSession.selectList("guest.getList");
		
		return gList;
	}
	
	// dao 방명록 추가
	public int insert(GuestVo guestVo) {
		System.out.println("2. GuestDao - 방명록 추가");
		
		return sqlSession.insert("guest.insert", guestVo);
	}
	
	// dao 방명록 삭제
	public int delete(GuestVo guestVo) {
		System.out.println("2. GuestDao - 방명록 삭제");

		return sqlSession.delete("guest.delete", guestVo);
	}
}
