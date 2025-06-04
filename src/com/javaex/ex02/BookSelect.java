package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelect {

	public static void main(String[] args) {
		
		List<BookVO> bList = new ArrayList<BookVO>();
		
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
			query += " select	book_id, ";
			query += " 			title, " ;
			query += " 			pubs, "; 
			query += " 			pub_date "; 
			query += " from book ";
		    
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
		    
		    // 4.결과처리
			while(rs.next()) {
				
				
				//ResultSet 의 데이터를 자바의 변수에 담는다
				//int authorId = rs.getInt("id");
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				
				/*
				//숫자(순서)로 해도 가능함
				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);
				*/
				
				//자바의 데이터를 VO로 묶는다
				BookVO bookVo = new BookVO(bookId, title, pubs, pubDate);
				//VO를 리스트에 추가(add) 한다
				bList.add(bookVo);
				
			}
			
			
			

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

		}// finally
		
		System.out.println("------------------------------------------------");
		
		for(int i=0; i<bList.size(); i++) {
			
			int bookId = bList.get(i).getBookId();
			String title = bList.get(i).getTitle();
			String pubs = bList.get(i).getPubs();
			String pubDate = bList.get(i).getPubDate();
			
			System.out.println(bookId + ". " + title + " "+ pubs + " " + pubDate);
			
			
		}
		System.out.println("------------------------------------------------");	
		
	}// main

}
