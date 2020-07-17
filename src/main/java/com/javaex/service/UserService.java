package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;


@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	// service 회원가입
	public int join(UserVo userVo) {
		System.out.println("2. UserService - join");
		
		return userDao.userInsert(userVo);
	}
	
	// service 로그인
	public UserVo login(UserVo userVo) {
		System.out.println("2. UserService - login");
		
		return userDao.selectUser(userVo);
	}
	
	// service 로그인 한 사용자 정보 가져오기
	public UserVo getUser(int no) {
		System.out.println("1. UserService - 사용자 정보 가져오기");
		
		return userDao.getUser(no);
	}
	
	// service 회원정보 수정
	public int update(UserVo userVo) {
		System.out.println("1. UserService - 회원정보 수정");
		
		return userDao.update(userVo);
	}
	
	// service 입력한 아이디 값과 중복되는 데이터가 있는 지 검사
	public boolean checkId(String id) {
		System.out.println("2. UserService - 중복체크");
		UserVo userVo = userDao.checkId(id);
		boolean result = true;
		
		if(userVo == null) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}
}
