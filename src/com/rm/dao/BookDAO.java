package com.rm.dao;

import java.util.List;

import com.rm.model.Book;

public interface BookDAO {
	public List<Book> getAll();
	
	public void setTimeBorrowedBook(int id, String date);
	public void payBook(int id);
}
