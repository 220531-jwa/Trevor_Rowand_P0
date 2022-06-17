package dev.rowand.service;
import java.util.List;
import dev.rowand.model.Client;
import dev.rowand.repo.UserDAO;
import io.javalin.http.Context;

/*
 * 
 * This UserService class is where we write
 * methods to perform any business logic that
 * our application needs to function appropriately
 * 
 */

public class UserService {
	
	private UserDAO UserDao = new UserDAO();
	
	public UserService(UserDAO userDao) {
		this.UserDao = userDao;
	}

	//login an existing user
	public Client login(String username, String password) {
		//we need to use our UserDAO to access the database
		Client c = UserDao.getUserByUsername(username);
		
		//check if that user even exists
		if(c.getPassword().equals(password)) {
			return c;
		}
		System.out.println("Credentials don't match.");
		return null;
	}
	//create a new user
	
	public Client createUser(Client c) {
		Client createdClient = UserDao.createUser(c);
		return createdClient;
	}
	
	public List<Client> getAllUsers() {
		return UserDao.getAllUsers();
	}

	public Client getUserById(int id) throws Exception{
		System.out.println(id);
		Client c = UserDao.getUserById(id);
		if(c == null) {
			throw new Exception("Client not found!");
		}
		return c;
	}

	public void updateUser(Client uChanged) throws Exception {
		// TODO Auto-generated method stub
		Client c = UserDao.getUserById(uChanged.getID());
		if(c == null) {
			throw new Exception("Client not found!");
		}
		UserDao.updateUser(uChanged);
		
		
	}

	public Client updatePassword(int id, String password) {
		// TODO Auto-generated method stub
		// check if that user exists
		return UserDao.updateUserPassword(id, password);
		
	}

	public void deleteUser(int id) throws Exception {
		// TODO Auto-generated method stub
		Client c = UserDao.getUserById(id);
		if(c == null) {
			throw new Exception("Client not found!");
		}
		UserDao.deleteUser(id);
		
	}

	
	//check that the username doesn't already exist
	
	//public List<Client>
	
	
	
}
