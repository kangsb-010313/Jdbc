package com.javaex.ex05;

public class BookApp {

	public static void main(String[] args) {
		
		//book 테이블만  가지고 연습
		
		BookDAO bookDAO = new BookDAO();
		
		// BookVO
		bookDAO.bookInsert();
		bookDAO.bookUpdate();
		bookDAO.bookDelete();
		bookDAO.bookSelect();
		bookDAO.bookSelectOne();
		
		////////////////////////////////////////////////
		// BookAuthorVO 
		bookDAO.bookSelectList(); --> 전체
		
		
	}

}
