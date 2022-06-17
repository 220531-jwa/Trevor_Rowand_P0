package dev.rowand.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//this class will define the methods needed to create a connection to our DB
//we are going to make ConnectionUtil using the Singleton Design Pattern
//to ensure that only 1 instance of the class exists throughout the program
public class ConnectionUtil {
	
	private static ConnectionUtil cu;
	private static Properties dbProps;

	
	
	//private constructor
	private ConnectionUtil() {
		//initailize the properties object to hold our db credentials
		dbProps = new Properties();
		
		//stream the credentials from our connections.properties file to this object
		InputStream props = ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties");
		
		try {
			dbProps.load(props);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//public getter to return us an instance of this class -> a ConnectionUtil
	public static synchronized ConnectionUtil getConnectionUtil() {
		//first check if an instance doesn't already exists
		if(cu == null) {
			cu = new ConnectionUtil();
		}
		//otherwise return the existing instance
		return cu;
	}
	//go back and look at the changes made to this file in the repo
	// Method to actually establish a connection to the db
	public Connection getConnection() {
		Connection conn = null;
		
		//if you're getting Driver not found or something similar - here's a hot fiz
		//forcing postgreSQL Driver to load
		try {
			Class.forName(dbProps.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//get our credentials from the ConnectionUtil's properties object made in the sonctructor
		String url = dbProps.getProperty("url");
		String username = dbProps.getProperty("username");
		String password = dbProps.getProperty("password");
		//use those credentials and the DriverManager to connect to our db instance
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		Connection connection = getConnectionUtil().getConnection();
		
		if(connection != null) {
			System.out.println("Connection successful!");
			
		}
		else {
			System.out.println("something went wrong...");
		}
		
		try {
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
