package com.javaex.ex05;

public class BookVO {

	//필드
	private int bookId;
	private String title;
	private String pubs;
	private String pubDb;

	
	//생성자
	public BookVO() {
	}
	public BookVO(int bookId, String title, String pubs, String pubDb) {
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubDb = pubDb;
	}
	
	
	//메소드 gs
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public String getPubDb() {
		return pubDb;
	}
	public void setPubDb(String pubDb) {
		this.pubDb = pubDb;
	}
	
	
	//메소드 일반
	@Override
	public String toString() {
		return "BookVO [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pubDb=" + pubDb + "]";
	}
	
	
}
