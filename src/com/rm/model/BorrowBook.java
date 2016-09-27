package com.rm.model;

public class BorrowBook {
	private int id;
	private String status;
	private String pay_date;
	private Book book;

	public BorrowBook(){}
	public BorrowBook(int id, String status, String pay_date) {
		super();
		this.id = id;
		this.status = status;
		this.pay_date = pay_date;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

}
