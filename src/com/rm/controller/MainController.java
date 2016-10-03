package com.rm.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rm.model.Book;
import com.rm.service.BookService;

@Controller
public class MainController {
	@Autowired
	private BookService bookService;       //or private BookDAO bookDAO is ok, too

	private List<Book> listBooks = new ArrayList<Book>(), borrowedBooks = new ArrayList<Book>();
	public static String today = getToday();

	
	@RequestMapping("/")
	public String home(ModelMap mm){	

		listBooks = bookService.getAll();

		int i, n = listBooks.size();
		String date;

		for(i = 0; i<n; i++){
			date = listBooks.get(i).getBorrowBook().getPay_date();

			if((date !=null) && (date.compareTo(today) < 0)) 
				listBooks.get(i).getBorrowBook().setStatus("over");		
		}

		mm.put("books", listBooks);	

		return "index";
	}

	
	@RequestMapping("/list-borrow-books")
	public String getBorrowedBooks(ModelMap mmodel){

		borrowedBooks = new ArrayList<Book>();

		for(Book b: listBooks)
			if(b.getBorrowBook().getStatus() != null) borrowedBooks.add(b);

		mmodel.put("borrowedBooks", borrowedBooks);
		//System.out.println("length of borrow: "+ borrowedBooks.size());

		return "list-borrow-books";
	}
	

	@RequestMapping("/add-borrow-book")
	public String setBorrowBook(@RequestParam("id") int id, @RequestParam("time") int time){

		//System.out.println("id-update = "+id);
		int days = 0;

		switch(time){
		case 0: days = 7; break;
		case 1: days = 30; break;
		case 2: days = 60; break;
		case 3: days = 90; break;
		}

		//System.out.println("time = " + days);
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		cal.setTime(new Date());
		cal.add(Calendar.DATE, days); //minus number would decrement the days
		
		String date = dateFormat.format(cal.getTime());

		bookService.setTimeBorrowedBook(id, date);
		updateStatusListBooksFullInfo(id, "yes", date);
		
		return "borrow-books";
	}
	

	@RequestMapping("/pay-book")
	public String payBook(@RequestParam("id") int id, ModelMap mmodel){
		
		bookService.payBook(id);
		updateStatusListBooksFullInfo(id, null, null);

		int n = borrowedBooks.size(), i = -1;
		
		try {
			while(++i < n){
				if(borrowedBooks.get(i).getId() == id) borrowedBooks.remove(i);
				break;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		mmodel.put("borrowedBooks", borrowedBooks);
		return "borrow-books";
	}


	private static String getToday(){
		
		Date today = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String present = dateFormat.format(today.getTime());
		
		return present;
	}
	

	private void updateStatusListBooksFullInfo(int id, String status, String date){
		
		if(listBooks != null){
			
			int i = -1, n = listBooks.size();
			
			while(++i < n)
				if(listBooks.get(i).getId() == id) break;

			listBooks.get(i).getBorrowBook().setStatus(status);
			listBooks.get(i).getBorrowBook().setPay_date(date);
		}
	}
}