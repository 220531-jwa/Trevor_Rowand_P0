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

public class AccountDAO {
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	public List<Account> getAllAccounts(int user_id, String query) { //add a user_id parameter here
		List<Account> accounts = new ArrayList<>();
		String sql;
		//sql statement to do executed
		System.out.println(query);
		if(query == "amountLessThan=2000&amountGreaterThan400") {
			sql = "select * from accounts where user_id = ? and balance < 2000 and balance > 400";
		}
		else {
			sql = "select * from accounts where user_id = ?";
		}
		
		//try with resources - this will auto close any resources you need without a finally block
		
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();	//is the entire table's worth of data	
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String type = rs.getString("type");
				float balance = rs.getFloat("balance");
				int userId = rs.getInt("user_id");
				//must have the same names as what is in the SQL file
				
				
			    Account a = new Account(id, type, balance, userId);
			    
			    //each loop of the while loop is a new object in the table
			    accounts.add(a);
			}
			return accounts;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Account getAccountById(int id, int user_id) { //add user_id
	//this question mark sybmolizes an IN parameter for our statement
	Account account = new Account();
	String sql = "select * from accounts where id = ? and user_id = ?";
		try(Connection conn = cu.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id); //where we are setting the "?" in our sql string to be the int id that's passed to this method as an int
			//can many many "?"; each one would get a number (1 for first, , 2 for 2nd, etc.  
			ps.setInt(2, user_id);
			
			ResultSet rs = ps.executeQuery();
			
			//if the result set has a row / record
			
			if(rs.next()) {
				return new Account(
						rs.getInt("id"),
						rs.getString("type"),
						rs.getFloat("balance"),
						rs.getInt("user_id")
						);
				
			}
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null; // optional class
	}
	public Account createAccount(Account a, int user_id) {
		String sql = "insert into accounts values (?, ?, ?, ?) returning *";
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql); //creates a object for sending SQL statements to the database
			ps.setInt(1, a.getId());
			ps.setString(2, a.getType()); //question marks indexes starts at 1
			ps.setFloat(3, a.getBalance());
			ps.setInt(4, a.getUserId()); //try making it a user to the account class
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Account(
						rs.getInt("id"),
						rs.getString("type"),
						rs.getFloat("balance"),
						rs.getInt("user_id")
						);	
		}} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public void deleteAccount(int id, int user_id) {
		String sql = "delete from accounts where id = ? and user_id = ?";
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, user_id);
			ps.execute(); //executes the prepared SQL statement
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void ExchangeAccount(float balancechng, Account a) { //needs to check if the body is either withdraw or deposit
		String sql = "update accounts set balance = ? where id = ? and user_id = ?";
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, balancechng);
			ps.setInt(2, a.getId());
			ps.setInt(3, a.getUserId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAccount(Account a) {
		// TODO Auto-generated method stub
	String sql = "update accounts set type = ?, balance = ? where id = ? and user_id = ?";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getType());
			ps.setFloat(2, a.getBalance());
			ps.setInt(3, a.getId());
			ps.setInt(4, a.getUserId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void transferFunds(Account a, Account b, Float aBalance, Float bBalance) {
		// TODO Auto-generated method stub
		String sql = "update accounts set balance = ? where id = ? and user_id = ?";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, aBalance);
			ps.setInt(2, a.getId());
			ps.setInt(3, a.getUserId());
			ps.executeUpdate();
			
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, bBalance);
			ps.setInt(2, b.getId());
			ps.setInt(3, b.getUserId());
			ps.executeUpdate();
			
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		
	} 
}
