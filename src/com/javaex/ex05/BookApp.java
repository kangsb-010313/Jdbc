package com.javaex.ex05;

public class BookApp {

	public static void main(String[] args) {
		
		//book 테이블만  가지고 연습
		
		BookDAO bookDAO = new BookDAO();
		
		// BookVO
		//int c01 = bookDAO.bookInsert("순정만화", "재미주의", "2011-08-03", 4);
		//int c02 = bookDAO.bookUpdate("26년", "재미주의", "2012-02-04", 9);
		int c03 = bookDAO.bookDelete();
		//List<BookVO> bList = bookDAO.bookSelect();
		//bookDAO.bookSelectOne();
		
		////////////////////////////////////////////////
		// BookAuthorVO 
		//BookVO bookVo = bookDAO.bookSelectList(); //--> 전체
		
		
	}

}
