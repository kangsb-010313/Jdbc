package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelectAll {

	public static void main(String[] args) {
		
		List<BookAuthorVO> baList = new ArrayList<BookAuthorVO>();
		
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
			String query = "";
			query += " select  b.book_id, ";
			query += " 		   b.title, ";
			query += " 		   b.pubs, ";
			query += " 		   b.pub_date, ";
			query += " 		   a.author_id, ";
			query += " 		   a.author_name, ";
			query += " 		   a.author_desc ";
			query += " from book b, author a ";
			query += " where b.author_id = a.author_id ";
			//System.out.println(query);
		    
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
		    // 4.결과처리
			while(rs.next()) {
				
				
				//ResultSet 의 데이터를 자바의 변수에 담는다
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date"); //자바에서는 날짜를 String 취급하면 된다
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				
				//자바의 데이터를 VO로 묶는다
				BookAuthorVO bookauthorVo = new BookAuthorVO(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
				//VO를 리스트에 추가(add) 한다
				baList.add(bookauthorVo);
				
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

		}//finally
		
		
		System.out.println("------------------------------------------------");
		
		for(int i=0; i<baList.size(); i++) {
			
			int bookId = baList.get(i).getBookId();
			String title = baList.get(i).getTitle();
			String pubs = baList.get(i).getPubs();
			String pubDate = baList.get(i).getPubDate();
			int authorId = baList.get(i).getAuthorId();
			String authorName = baList.get(i).getAuthorName();
			String authorDesc = baList.get(i).getAuthorDesc();
			
			System.out.println(bookId + ". " + title + " "+ pubs + " " + pubDate + " " + authorId + ". " + authorName + " " + authorDesc);
			
			
		}
		System.out.println("------------------------------------------------");	
		
	}//main

}
