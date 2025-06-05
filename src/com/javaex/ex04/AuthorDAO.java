package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorDAO {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/web_db";
	private String id = "web";
	private String pw = "web";
	
	
	// 생성자
	public AuthorDAO() {
	}

	// 메소드 gs

	
	
	
	// 메소드 일반
	//DB연결 메소드
	private void connect() { //메인에서는 사용하지 못함
		
		try {
			
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}


		
	}
	
	//자원정리 메소드-공통
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
	
	

	// 작가 등록 /////////////////////////////////////////////////////////
	public int authorInsert(String name, String desc) {

		int count = -1;

		
		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		this.connect();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into author ";
			query += " values(null, ?, ?) ";
			System.out.println(query);

			// 바인딩ff
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name); // 메소드의 파라미터
			pstmt.setString(2, desc); // 메소드의 파라미터 (외부에서 들어올 수 있게 해줌)

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		// 5. 자원정리
		this.close();

		return count;

	}

	
	
	// 작가 수정 /////////////////////////////////////////////////////////
	public int authorUpdate(int authorId, String name, String desc) {

		int count = -1;

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		this.connect();
		
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += " author_desc = ? ";
			query += " where author_id = ? ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			pstmt.setInt(3, authorId);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리


		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();

		return count;
	}
	
	

	// 작가 삭제 /////////////////////////////////////////////////////////
	public int authorDelete(int authorId) {

		int count = -1;

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect();
		
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비 ? 작업
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 

		this.close();
		
		return count;

	}// delete

	
	
	// 작가 리스트 /////////////////////////////////////////////////////////
	public List<AuthorVO> authorSelect() {
		
		// VO 벨류 오브젝트
		List<AuthorVO> authorList = new ArrayList<AuthorVO>();

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		this.connect();
		

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select	author_id, ";
			query += " 			author_name, ";
			query += " 			author_desc ";
			query += " from author ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리 (자바 리스트로 만든다)
			while (rs.next()) {

				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");


				// 데이터 객체로 만들기 (묶기)
				AuthorVO authorVO = new AuthorVO(authorId, authorName, authorDesc);

				// 묶은 테이블을 리스트에 담기
				authorList.add(authorVO);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();

		return authorList;
		
	}// select
	
	
	
	//작가 번호로 조회
	public AuthorVO authorSelectOne(int authorId) {

		AuthorVO authorVo = null;
		
		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		this.connect();
		

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select	author_id, ";
			query += " 			author_name, ";
			query += " 			author_desc ";
			query += " from author ";
			query += " where author_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리 (자바 리스트로 만든다)
			rs.next();
			
			int id = rs.getInt("author_id");
			String name = rs.getString("author_name");
			String desc = rs.getString("author_desc");
			
			authorVo = new AuthorVO(id, name, desc);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();
		
		return authorVo;

		
	}//authorSelectOne
	
	
	
	

}
