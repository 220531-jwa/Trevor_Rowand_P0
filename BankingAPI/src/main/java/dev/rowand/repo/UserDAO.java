package dev.rowand.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.rowand.model.Account;
import dev.rowand.model.Client;
import dev.rowand.utils.ConnectionUtil;
import io.javalin.http.Context;

public class UserDAO {
private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

public Client createUser(Client c) {
	String sql = "insert into users values (?, ?, ?, ?, ?) returning *";
	try(Connection conn = cu.getConnection()){
		PreparedStatement ps = conn.prepareStatement(sql); //creates a object for sending SQL statements to the database
		ps.setInt(1, c.getID());
		ps.setString(2, c.getFirstname()); //question marks indexes starts at 1
		ps.setString(3, c.getLastname());
		ps.setString(4, c.getUsername());
		ps.setString(5, c.getPassword());
		
		
		ResultSet rs = ps.executeQuery();
		//what to change for account equivalents: 
		//MAKE SURE ALL NAMES ARE IDENTICAL FROM POSTMAN AND JAVA
		//REPLACE DEFAULT WITH C.GETID
		
		
		if (rs.next()) {
			return new Client(
					rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getString("pass")
					);	
	}} catch(SQLException e) {
		e.printStackTrace();
	}
	
	return null;
}

public List<Client> getAllUsers(){
	//create an empty array list that will hold all users returned from the database
	List<Client> users = new ArrayList<>();
	//sql statement to do executed
	String sql = "select * from users";
	//try with resources - this will auto close any resources you need without a finally block
	
	try (Connection conn = cu.getConnection()){
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();	//is the entire table's worth of data	
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String username = rs.getString("username");
			String password = rs.getString("pass");
			//must have the same names as what is in the SQL file
			
			
		    Client c = new Client(id, firstName, lastName, username, password);
		    
		    //each loop of the while loop is a new object in the table
		    users.add(c);
		}
		return users;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
}
public Client getUserById(int id) {
	String sql = "select * from users where id = ?"; //this question mark sybmolizes an IN parameter for our statement
	
	try(Connection conn = cu.getConnection()){
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id); //where we are setting the "?" in our sql string to be the int id that's passed to this method as an int
		//can many many "?"; each one would get a number (1 for first, , 2 for 2nd, etc.  
		ResultSet rs = ps.executeQuery();
		
		//if the result set has a row / record
		if(rs.next()) {
			return new Client(
					
					rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getString("pass")
					);
			
		}
		
		
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return null; // optional class
}

public Client getUserByUsername(String username) {
/*String sql = "select * from users where username = ?"; //this question mark sybmolizes an IN parameter for our statement
	
	try(Connection conn = cu.getConnection()){
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username); //where we are setting the "?" in our sql string to be the int id that's passed to this method as an int
		//can many many "?"; each one would get a number (1 for first, , 2 for 2nd, etc.  
		
		ResultSet rs = ps.executeQuery();
		
		//if the result set has a row / record
		
		if(rs.next()) {
			Client c = new Client(
					rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getString("password")
					);
		}
		
		
	} catch(SQLException e) {
		e.printStackTrace();
	} */
	return null; // optional class
}

public void updateUser(Client c) {
	String sql = "update users set first_name = ?, last_name = ?, username = ?, pass = ? where id = ?";
	
	try(Connection conn = cu.getConnection()){
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, c.getFirstname());
		ps.setString(2, c.getLastname());
		ps.setString(3, c.getUsername());
		ps.setString(4, c.getPassword());
		ps.setInt(5, c.getID());
		
		ps.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
}

public void deleteUser(int id) {
	String sql = "delete from users where id = ?";
	try(Connection conn = cu.getConnection()){
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute(); //executes the prepared SQL statement
	
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public Client updateUserPassword(int id, String password) {
	String sql = "update users set pass = ? where id = ?";
	try(Connection conn = cu.getConnection()){
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, password);
		ps.setInt(2, id);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return new Client(
					rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getString("pass")
			);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return null;
}





}
