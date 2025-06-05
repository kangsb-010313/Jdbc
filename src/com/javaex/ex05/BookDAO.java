package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDAO {

	
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	//private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/web_db";
	private String id = "web";
	private String pw = "web";
	
	
	//생성자
	public BookDAO() {
	}	
	
	//메소드 gs
	
	
	//메소드 일반 ///////////////////////////////////////////////
	
	//DB연결 메소드
	private void connect() {
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}
	
	//자원 정리 메소드
	private void close() {
		
		// 5. 자원정리
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}
	
	// //////////////////////////////////////////////////////////////////////
	
	//책 등록
	public int bookInsert(String title, String pubs, String pubDate, int authorId) {
		int count = -1;
		
		// 0. import java.sql.*;

		
	    // 1. JDBC 드라이버 (Oracle) 로딩
	    // 2. Connection 얻어오기
		this.connect();

		try {


		    // 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into book ";
			query += " values(null, ?, ?, ?, ?) "; 
			System.out.println(query); 

			// 바인딩ff
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
		    
			//실행
			count = pstmt.executeUpdate();
		    
		    // 4.결과처리
			
		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		this.close();
		
		return count;
		
	}
	

	
	//책 수정
	public int bookUpdate(String title, String pubs, String pubDate, int bookId) {
		
		int count = -1;
		
		// 0. import java.sql.*;

	    // 1. JDBC 드라이버 (Oracle) 로딩
	    // 2. Connection 얻어오기
		this.connect();

		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " update book ";
			query += " set title = ?, " ;
			query += " 	   pubs = ?, "; 
			query += " 	   pub_date = ? "; 
			query += " where book_id = ? ";
			System.out.println(query);
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, bookId);
			
			//실행
			count = pstmt.executeUpdate();
			
		    
		    // 4.결과처리
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		this.close();
		
		return count;
		
	}
	
	
	/*
	//책 삭제
	public int bookDelete(int bookId) {
		int count = -1;
		
		
	}
	
	
	//책 조회
	
	
	*/
	

}
