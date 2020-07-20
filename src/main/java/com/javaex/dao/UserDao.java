package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;

	// dao 회원가입
	public int userInsert(UserVo userVo) {
		System.out.println("3. UserDao - userInsert");
		
		return sqlSession.insert("user.userInsert", userVo);
	}
	
	// dao 로그인
	public UserVo selectUser(UserVo userVo) {
		System.out.println("3. UserDao - login");
		
		return sqlSession.selectOne("user.selectUser", userVo);
	}

	// dao 로그인 한 사용자 정보 가져오기
	public UserVo getUser(int no) {
		System.out.println("2. UserDao - 사용자 정보 가져오기");
		
		return sqlSession.selectOne("user.getUser", no);
	}
	
	// dao 회원정보 수정
	public int update(UserVo userVo) {
		System.out.println("2. UserDao - 회원정보 수정");
		
		return sqlSession.update("user.update", userVo);
	}
	
	// dao 입력한 아이디 값과 중복되는 데이터가 있는 지 검사
	public UserVo checkId(String id) {
		System.out.println("3. UserDao - 중복체크");
		return sqlSession.selectOne("user.selectById", id);
	} 
}
