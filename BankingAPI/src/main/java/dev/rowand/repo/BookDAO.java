package dev.rowand.repo;

import java.sql.Connection;

import dev.rowand.utils.ConnectionUtil;

private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

public class BookDAO {
//CRUD
	public List<Book> getBooksByUserId(int id){
		//CRUD
		String sql = "select * from users_books ub"
				+ "left join clients c on ub.users_id = c.id"
				+ "left join books b on ub.users_id = b.id"
				+ "where user_id = ?";
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
		}
		}
}
