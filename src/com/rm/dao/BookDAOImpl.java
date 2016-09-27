package com.rm.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rm.model.Book;

@Repository
@Transactional
public class BookDAOImpl implements BookDAO {
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<Book> getAll(){
		String hql = "select b from Book b inner join b.information i inner join b.category inner join b.borrowBook";
		/*List<Object[]> entities = this.sessionFactory.openSession().createSQLQuery(sql).list();
		for (Object[] e : entities) 
			list.add(new BookFullInfo((int)e[0],(String)e[1],(String)e[2],(String)e[3],(String)e[4],(String)e[5],
					(String)e[6],(String)e[7],(String)e[8],(String)e[9],(String)e[10],(String)e[11]));*/
		
		@SuppressWarnings("unchecked")
		List<Book> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	
	@Override
	public void setTimeBorrowedBook(int id, String date){
		String sql="INSERT INTO borrow_books(id,status,pay_date) VALUES ("+id+",'yes', '"+date+"')"
				+ " ON DUPLICATE KEY UPDATE status='yes', pay_date='"+date+"'";

		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	
	@Override
	public void payBook(int id){
		String hql = "UPDATE BorrowBook bb set bb.status = null, bb.pay_date = null WHERE id = " +id;
		this.sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}

}