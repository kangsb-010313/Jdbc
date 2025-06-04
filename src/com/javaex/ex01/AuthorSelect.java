package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {

	public static void main(String[] args) {
		
		//VO 벨류 오브젝트
		List<AuthorVO> aList = new ArrayList<AuthorVO>();
		
		
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
			//query += " select	author_id as id, ";
			query += " select	author_id, ";
			query += " 			author_name, " ;
			query += " 			author_desc "; 
			query += " from author ";
			// System.out.println(query); -- test
		    
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
		    // 4.결과처리
			//rs에 표가 들어가 있다(사용이 불편함) --> 리스트에 담는다 
			while(rs.next()) {
				
				
				//ResultSet 의 데이터를 자바의 변수에 담는다
				//int authorId = rs.getInt("id");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				/*
				//숫자(순서)로 해도 가능함
				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);
				*/
				
				//자바의 데이터를 VO로 묶는다
				AuthorVO authorVO = new AuthorVO(authorId, authorName, authorDesc);
				
				//VO를 리스트에 추가(add) 한다
				aList.add(authorVO);
				
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

		/*
		for(int i=0; i<aList.size(); i++) {
			
			System.out.println(aList.get(i).toString());
		}
		*/
		
		//맨 앞 데이터 이름만 출력
		//System.out.println(aList.get(0).getAuthorName());
		
		/*
		------------------------------------
		1. 이문열 (서울)
		2. 이찬영 (미국)
		------------------------------------
		*/
		
		
		
		/*
		System.out.println("------------------------------------");
		
		for(int i=0; i<aList.size(); i++) {
			System.out.print(aList.get(i).getAuthorId() + ". ");
			System.out.print(aList.get(i).getAuthorName());
			System.out.println("(" + aList.get(i).getAuthorDesc() + ")");
		}
		System.out.println("------------------------------------");	
		*/
		System.out.println("------------------------------------");
		
		for(int i=0; i<aList.size(); i++) {
			
			int authorId = aList.get(i).getAuthorId();
			String authorName = aList.get(i).getAuthorName();
			String authorDesc = aList.get(i).getAuthorDesc();
			
			System.out.println(authorId + ". " + authorName + "(" + authorDesc + ")");
		}
		System.out.println("------------------------------------");	
		
		
	}//main

}
