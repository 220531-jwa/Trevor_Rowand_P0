package dev.rowand.utils;

import java.util.*;


import dev.rowand.model.Client;

public class MockDB {

	public static List<Client> clients = new ArrayList<>();
	
	//static code blocks will execute when the class is loaded
	static {
		clients.add(new Client(1, "Tim", "Tim", "user1", "passpass"));
		clients.add(new Client(1, "Travis", "T-Down", "user2", "pass2"));
		clients.add(new Client(1, "Hermes", "Apollo", "user3", "pass"));
		clients.add(new Client(1, "Frankie", "Rose", "user4", "passpasspass"));
		clients.add(new Client(1, "Thomas", "Engine", "user5", "pass2pass"));
		
	} //workaround, pseudo DB (literal mock DB)
	//repo will interact with DB
}
