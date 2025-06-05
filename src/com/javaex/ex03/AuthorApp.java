package com.javaex.ex03;

import java.util.List;

// 데이터 담는 전용 박스
public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDAO authorDao = new AuthorDAO();

		
		//작가 등록 /////////////////////////////
		
		/*
		int count = authorDao.authorInsert("송은석", "서울");
		if(count>0) {
			System.out.println("등록되었습니다.");
		}else if(count<0) {
			System.out.println("알 수 없는 오류 발생");
		}else {
			System.out.println("등록되지 않았습니다.");
		}
		
		authorDao.authorInsert("박경리", "토지작가");
		authorDao.authorInsert("김영하", "알쓸신잡 출연");
		authorDao.authorInsert("기안84", "방송대상 수상");
		*/
		
		
		
		//작가 수정 /////////////////////////////
		
		/*
		int count = authorDao.authorUpdate(10, "이소희", "보컬");
		if(count>0) {
			System.out.println("수정되었습니다.");
		}else if(count<0) {
			System.out.println("알 수 없는 오류 발생");
		}else {
			System.out.println("수정되지 않았습니다.");
		}
		*/
		
		
		
		//작가 삭제 /////////////////////////////
		
		/*
		int count = authorDao.authorDelete(11);
		if(count>0) {
			System.out.println("삭제되었습니다.");
		}else if(count<0) {
			System.out.println("알 수 없는 오류 발생");
		}else {
			System.out.println("삭제되지 않았습니다.");
		}
		*/
		
		
		
		//전체 리스트 /////////////////////////////
		
		List<AuthorVO> authorList = authorDao.authorList();
		
		//사용자용 출력화면
		for(int i=0; i<authorList.size(); i++) {
			AuthorVO authorVo = authorList.get(i);
			
			
			System.out.println(authorVo.getAuthorId() + "." + authorVo.getAuthorName() + "(" + authorVo.getAuthorDesc() + ")");
		}
		
		
		
		
		//꾸미기는 DAO에서 배제시킨다
		/*
		1. 쇼타로 (일본)
		2. 이찬영 (미국)
		*/
		
	}

}
