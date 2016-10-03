package com.rm.model;

public class Book {
	
	private int id;
	private String name;
	private String author;
	private Information information;
	private BorrowBook borrowBook;
	private Category category;

	public Book() {}
	
	public Book(String name, String author) {
		this.name = name;
		this.author = author;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Information getInformation() {
		return information;
	}
	public void setInformation(Information information) {
		this.information = information;
	}
	public BorrowBook getBorrowBook() {
		return borrowBook;
	}
	public void setBorrowBook(BorrowBook borrowBook) {
		this.borrowBook = borrowBook;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}