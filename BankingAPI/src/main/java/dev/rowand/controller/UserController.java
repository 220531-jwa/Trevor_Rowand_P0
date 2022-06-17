package dev.rowand.controller;

import java.util.List;

import dev.rowand.model.Account;
import dev.rowand.model.Client;
import dev.rowand.repo.UserDAO;
import dev.rowand.service.UserService;
import io.javalin.http.Context;

public class UserController {
	
	private static UserService us = new UserService(new UserDAO());

	public static void getAllUsers(Context ctx){
		ctx.status(200);
		
		List<Client> clients = us.getAllUsers(); //make this user service
		ctx.json(clients);
	}
	public static void createNewUser(Context ctx) {
		ctx.status(201);
		Client userFromRequestBody = ctx.bodyAsClass(Client.class);
		Client c = us.createUser(userFromRequestBody); //unmarshalling
		ctx.json(c);
	}
	
	public static void getUserById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Client c = null;
		try {
			c = us.getUserById(id); //MAKE SURE C GETS THIS VALUE
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		ctx.status(200);
		ctx.json(c);
	}
	
	public static void deleteUser(Context ctx) {
	   int id = Integer.parseInt(ctx.pathParam("id"));
	   try {
		   us.deleteUser(id);
	   } catch(Exception e) {
		   e.printStackTrace();
	   }
	   
	   ctx.status(205);
	   
		
	}
	public static void updateUser(Context ctx) {
		Client uChanged = ctx.bodyAsClass(Client.class); //unmarshalling
		System.out.println("updateUser -= " + uChanged);
		try {
			us.updateUser(uChanged);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void updatePassword(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Client c = ctx.bodyAsClass(Client.class); // {"password": "newPassword"}
		System.out.println(c.getPassword());
		us.updatePassword(id, c.getPassword());
		
	}

	
	
}
