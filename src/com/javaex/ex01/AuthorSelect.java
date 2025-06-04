package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelect {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

		    // 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/web_db";
			conn = DriverManager.getConnection(url, "web", "web");

		    // 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비 
			String query = "";
			query += " select	author_id, ";
			query += " 			author_name, " ;
			query += " 			author_desc, "; 
			query += " from author ";
			System.out.println(query);
		    
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
			
		    // 4.결과처리
			//rs에 표가 들어가 있다(사용이 불편함) --> 리스트에 담는다 
			

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
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

		
		
		
	}

}
