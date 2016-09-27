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
	//private BookDAO bookDAO;
	private BookService bookService;
	private static List<Book> listBooks;
	private List<Book> borrowedBooks;
	public static String today;

	public MainController(){
		listBooks = new ArrayList<Book>();
		borrowedBooks = new ArrayList<Book>();
		today = getToday();
	}

	@RequestMapping("/")
	public String home(ModelMap mm){	

		listBooks = bookService.getAll();
		int n = listBooks.size();
		int i;
		String date;
		for(i = 0; i<n;i++){
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
		if(time == 0) days = 7;
		else if(time == 1) days = 30;
		else if(time == 2) days = 60;
		else days = 90;

		//System.out.println("time = " + days);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, days); //minus number would decrement the days
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

	private static void updateStatusListBooksFullInfo(int id, String status, String date){
		if(listBooks != null){
			int i = -1, n = listBooks.size();
			while(++i < n)
				if(listBooks.get(i).getId() == id) break;

			listBooks.get(i).getBorrowBook().setStatus(status);
			listBooks.get(i).getBorrowBook().setPay_date(date);
		}
	}
}