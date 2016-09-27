package management.borrow.book.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import management.borrow.book.controller.MainController;
import management.borrow.book.model.Book;

public class BookMapper implements RowMapper<Book> {
	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id=rs.getInt(1);
		String name=rs.getString(2);
		String author=rs.getString(3);
		String category=rs.getString(4);
		String date=rs.getString(5);
		String publisher=rs.getString(6);
		String language=rs.getString(7);
		String pages=rs.getString(8);
		String img=rs.getString(9);
		String info=rs.getString(10);
		String status=rs.getString(11);
		String pay_date=rs.getString(12);
		
		if((pay_date != null) && MainController.today.compareTo(pay_date) > 0) status="over";

		return new Book(id, name, author, category, date, publisher, language, pages, img, info, status, pay_date);
	}
}
