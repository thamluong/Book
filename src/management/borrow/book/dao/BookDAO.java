package management.borrow.book.dao;
import java.util.List;

import management.borrow.book.model.Book;

public interface BookDAO {
	public List<Book> getBooks();
	public List<Book> getBorrowedBooks();
	public void setTimeBorrowedBook(int id, String date);
	public void payBook(int id);
}
