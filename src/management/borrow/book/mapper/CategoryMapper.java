package management.borrow.book.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import management.borrow.book.model.Category;

public class CategoryMapper implements RowMapper<Category> {
	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id=rs.getInt(1);
		String name=rs.getString(2);
		return new Category(id, name);
	}
}