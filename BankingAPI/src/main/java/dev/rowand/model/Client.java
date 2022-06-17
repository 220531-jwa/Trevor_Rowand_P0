package dev.rowand.model;
//POJO - Plain Old Java Object
//private instance variables with public getters and setters
//and a no args constructors
public class Client {
	private int ID;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	
	public Client() {}
	

	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Client(int iD, String firstname, String lastname, String username, String password) {
		super();
		ID = iD;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Client [ID=" + ID + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	
	
	
	
	
	
}
