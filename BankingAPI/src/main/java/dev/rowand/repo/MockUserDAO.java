package dev.rowand.repo;

import java.util.List;

import dev.rowand.model.Client;
import dev.rowand.utils.MockDB;

/*DAO Design Pattern
 * 
 * DAO = Data Access Object
 * This class defines the methods to be used
 * to interact with the database (for now our mock database)
 * 
 * 
 */
public class MockUserDAO {

	/*
	 * CRUD Methods
	 * 
	 * Create - Craete a new record in our database
	 *           -(register a new User)
	 * 
	 * Read - Read (query) data from our DB
	 *        - i.e. user wants to change thier password
	 * 
	 * Update - Update an existing record in the DB
	 *         - i.e. user wants to change thier password
	 * 
	 * Delete - Delete an existing record in our DB
	 *         -i.e. user wants to delete thier account
	 * 
	 * C-R-U-D
	 * 
	 * 
	 * 
	 * 
	 */
	
	//Read
	
	//List All
	
	public List<Client> getAllUsers(){
		return MockDB.clients;
	}
	
	public Client getUserById(int id) {
		/* for(Client c : MockDB.clients) {
			if(id == c.getID()) {
				return c;
			}
			else {
				System.out.println("User not found");
				return null;
			} all of this to find multiple objects
		} */
		Client c = MockDB.clients.get(id-1);
		return c; //to find 1 object
	}
	//create a client
	public Client createUser(Client c) { //classes can be used as types in methods
		MockDB.clients.add(c); //add to database
		return c; //return to method
	}

	public Client getUserByUsername(String username) {
		for(Client c : MockDB.clients) {
			if(c.getUsername().equals(username)) {
				return c;
			}
		}
		return null;
	}
	
	//to create a UserDAO ref. var.
	//private static UserDAO userDao;
	//userDao = new UserDAO();
	
	//List<Clients> users = userDao.getAllUsers(); in main method
	//sysout users afterwards
	
	
	//Update
	
	//Delete

	
}
