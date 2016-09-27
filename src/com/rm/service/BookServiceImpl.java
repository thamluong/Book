package com.rm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rm.dao.BookDAO;
import com.rm.model.Book;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDAO bookDAO;

	@Autowired
	public void setUserDao(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@Override
	public List<Book> getAll(){
		return bookDAO.getAll();
	}

	@Override
	public void setTimeBorrowedBook(int id, String date){
		bookDAO.setTimeBorrowedBook(id, date);
	}

	@Override
	public void payBook(int id){
		bookDAO.payBook(id);
	}

}