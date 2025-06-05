package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookDAO {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/web_db";
	private String id = "web";
	private String pw = "web";

	// 생성자
	public BookDAO() {
	}

	// 메소드 gs

	// 메소드 일반 ///////////////////////////////////////////////

	// DB연결 메소드
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
	

	// 자원 정리 메소드
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

	// 책 등록 /////////////////////////////////////
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

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;

	}// insert
	
	

	// 책 수정 /////////////////////////////////////
	public int bookUpdate(String title, String pubs, String pubDate, int bookId) {

		int count = -1;

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		this.connect();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " update book ";
			query += " set title = ?, ";
			query += " 	   pubs = ?, ";
			query += " 	   pub_date = ? ";
			query += " where book_id = ? ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, bookId);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;

	}// update
	
	

	// 책 삭제 /////////////////////////////////////
	public int bookDelete(int bookId) {
		int count = -1;

		// 0. import java.sql.*;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;

	}// delete

	// 책 조회 /////////////////////////////////////
	public List<BookVO> bookSelect() {

		List<BookVO> bList = new ArrayList<BookVO>();

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		this.connect();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select	book_id, ";
			query += " 			title, ";
			query += " 			pubs, ";
			query += " 			pub_date ";
			query += " from book ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");

				// 데이터 객체로 만들기 (묶기)
				BookVO bookVo = new BookVO(bookId, title, pubs, pubDate);
				// 묶은 테이블을 리스트에 담기
				bList.add(bookVo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return bList;

	}// select
	
	
	//책 번호로 조회
	public BookVO bookSelectOne(int bookId) {

		BookVO bookVo = null;
		
		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		this.connect();
		

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select	book_id, ";
			query += " 			title, ";
			query += " 			pubs, ";
			query += " 			pub_date ";
			query += " from book ";
			query += " where book_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리 (자바 리스트로 만든다)
			rs.next();
			
			int id = rs.getInt("book_id");
			String title = rs.getString("title");
			String pubs = rs.getString("pubs");
			String pubDate = rs.getString("pub_date");
			
			bookVo = new BookVO(id, title, pubs, pubDate);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();
		
		return bookVo;

		
	}//bookSelectOne
	
	

	//책, 작가 전체 정보 조회
	public List<BookAuthorVO> bookSelectList() {
		
		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		this.connect();
		
		
	}
	
	
}
