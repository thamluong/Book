package management.borrow.book.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import management.borrow.book.dao.BookDAO;
import management.borrow.book.model.Book;

@Controller
public class MainController {
	@Autowired
	private BookDAO bookDAO;
	private static List<Book> borrowedBooks = new ArrayList<Book>();
	public static String today = getToday();

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String listBooks(ModelMap mmodel) {
		mmodel.put("books", bookDAO.getBooks());
		return "index";
	}

	@RequestMapping(value="/list-borrow-books", method= RequestMethod.GET)
	public String getBorrowedBooks(ModelMap mmodel){
		borrowedBooks = bookDAO.getBorrowedBooks();
		mmodel.put("borrowedBooks", borrowedBooks);
		return "list-borrow-books";
	}

	@RequestMapping(value="/add-borrow-book")
	public String setBorrowedBooks(@RequestParam("id") int id, @RequestParam("time") int time){
		System.out.println("id-update = "+id);
		int days = 0;
		if(time == 0) days = 7;
		else if(time == 1) days = 30;
		else if(time == 2) days = 60;
		else days = 90;

		System.out.println("time = " + days);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, days); //minus number would decrement the days
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		bookDAO.setTimeBorrowedBook(id, dateFormat.format(cal.getTime()));
		return "borrow-books";
	}

	@RequestMapping(value="/pay-book")
	public String payBook(@RequestParam("id") int id, ModelMap mmodel) {
		bookDAO.payBook(id);
		borrowedBooks = bookDAO.getBorrowedBooks();
		mmodel.put("borrowedBooks", borrowedBooks);
		return "borrow-books";
	}

	private static String getToday(){
		Date today = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String present = dateFormat.format(today.getTime());
		return present;
	}
}
