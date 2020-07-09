package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestService {
	@Autowired
	private GuestDao guestDao;
	
	// Service 방명록 리스트 가져오기
	public List<GuestVo> getList() {
		System.out.println("1. GuestService - 리스트 가져오기");
		
		return guestDao.getList();
	}
	
	// Service 방명록 추가
	public int write(GuestVo guestVo) {
		System.out.println("1. GuestService - 방명록 추가");

		System.out.println(guestVo.toString());
		
		return guestDao.insert(guestVo);
	}
	
	// Service 방명록 삭제
	public int delete(GuestVo guestVo) {
		System.out.println("1. GuestService - 방명록 삭제");
		
		return guestDao.delete(guestVo);
	}
}
