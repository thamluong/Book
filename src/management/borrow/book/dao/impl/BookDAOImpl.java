package management.borrow.book.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import management.borrow.book.dao.BookDAO;
import management.borrow.book.mapper.BookMapper;
import management.borrow.book.model.Book;

@Service
@Transactional
public class BookDAOImpl extends JdbcDaoSupport implements BookDAO{

	@Autowired
	public BookDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public List<Book> getBooks(){
		String sql = "SELECT book.*,information.category,information.date,information.publisher,information.language,"
				+ " information.pages,information.img,information.info, borrow_books.status, borrow_books.pay_date"
				+ " FROM bookstore.information"
				+ " join (bookstore.book join bookstore.borrow_books using(id)) using (id)"
				+ " join bookstore.category on information.category=category.id";
		List<Book> list = this.getJdbcTemplate().query(sql, new BookMapper());
		return list;
	}

	@Override
	public List<Book> getBorrowedBooks(){
		String sql = "SELECT book.*,information.category,information.date,information.publisher,information.language,"
				+ " information.pages,information.img,information.info, borrow_books.status, borrow_books.pay_date"
				+ " FROM bookstore.information"
				+ " join (bookstore.book join bookstore.borrow_books using(id)) using (id)"
				+ " join bookstore.category on information.category=category.id"
				+ " where status is not null";
		return this.getJdbcTemplate().query(sql, new BookMapper());
	}

	@Override
	public void setTimeBorrowedBook(int id, String date){
		System.out.println("id="+id+", date="+date);
		System.out.println("Here is udpate add");
		String sql="INSERT INTO borrow_books(id,status,pay_date) VALUES ("+id+",'yes', '"+date+"')"
				+ " ON DUPLICATE KEY UPDATE status='yes', pay_date='"+date+"'";

		this.getJdbcTemplate().update(sql);
	}

	@Override
	public void payBook(int id){
		String sql = "UPDATE bookstore.borrow_books SET status=null, pay_date=null WHERE id="+id;
		this.getJdbcTemplate().update(sql);
	}
}
