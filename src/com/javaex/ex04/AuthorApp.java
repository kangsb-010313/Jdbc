package com.javaex.ex04;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDAO authorDAO = new AuthorDAO();
		
		
		//int c01 = authorDAO.authorInsert("황선우", "강원도청");
		
		//int c02 = authorDAO.authorUpdate(16, "남윤수", "서울");
		
		//int c03 = authorDAO.authorDelete(15);
		
//		List<AuthorVO> authorList = authorDAO.authorSelect();
//		
//		for(int i=0; i<authorList.size(); i++) {
//			System.out.println(authorList.get(i));
//		}
		
		AuthorVO authorVo = authorDAO.authorSelectOne(4);
		System.out.println(authorVo);
	}

}
