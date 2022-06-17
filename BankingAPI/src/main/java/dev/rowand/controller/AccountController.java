package dev.rowand.controller;

import java.util.List;

//String queryParam = ctx.queryParam("(insert the words after ? but before = )");
//get this set up for the method tied to the query param for the accounts path

import dev.rowand.model.Account;
import dev.rowand.repo.UserDAO;
import dev.rowand.service.AccountService;
import dev.rowand.service.UserService;
import io.javalin.http.Context;

public class AccountController {
	
	private static AccountService as = new AccountService();
	private static UserService us = new UserService(new UserDAO());
	
	public static void getAllAccounts(Context ctx) {
		int user_id = Integer.parseInt(ctx.pathParam("id")); //if this doesn't return u id, try to find it in service
		try {
			us.getUserById(user_id);
		} catch(Exception e){
			e.printStackTrace();
		}
		String queryParam = ctx.queryParam("amountLessThan=2000&amountGreaterThan400");
		System.out.println(queryParam);
		
		List<Account> accounts = as.getAllAccounts(user_id, queryParam); //make this user service
		ctx.json(accounts);
	}
public static void createNewAccount(Context ctx) {
		ctx.status(201);
		int user_id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println(user_id);
		try {
			us.getUserById(user_id);
		} catch(Exception e){
			e.printStackTrace();
		}
		Account AccountFromRequestBody = ctx.bodyAsClass(Account.class);
		Account a = as.createAccount(AccountFromRequestBody, user_id);
		ctx.json(a);
	}
public static void getAccountById(Context ctx) { //add user id
	int id = Integer.parseInt(ctx.pathParam("AccountNum"));
	int user_id = Integer.parseInt(ctx.pathParam("id"));
	try {
		us.getUserById(user_id);
	} catch(Exception e){
		e.printStackTrace();
	}
	Account a = null;
	try {
		a = as.getAccountById(id, user_id);
	} catch(Exception e) {
		e.printStackTrace();
	}
	ctx.json(a);
}

public static void updateAccount(Context ctx) {
	Account aChanged = ctx.bodyAsClass(Account.class);
	try {
		us.getUserById(aChanged.getUserId());
	} catch (Exception e) {
		e.printStackTrace();
	}
	System.out.println("updateAccount -= " + aChanged);
	try {
		as.updateAccount(aChanged);
	} catch(Exception e) {
		e.printStackTrace();
	}
	
}
public static void deleteAccount(Context ctx) {
	int id = Integer.parseInt(ctx.pathParam("AccountNum"));
	int user_id = Integer.parseInt(ctx.pathParam("id"));
	try {
		us.getUserById(user_id);
	} catch(Exception e){
		e.printStackTrace();
	}
	try {
		as.deleteAccount(id, user_id);
	} catch(Exception e) {
		e.printStackTrace();
	}
}
public static void ExchangeAccount(Context ctx) throws Exception { //make it like a normal getuserbuid to get the balance then sub it?
	int id = Integer.parseInt(ctx.pathParam("AccountNum"));
	int user_id = Integer.parseInt(ctx.pathParam("id"));
	Account a = null;
	try {
		as.getAccountById(id, user_id);
	} catch(Exception e) {
		e.printStackTrace();
	}
	String amount = ctx.body(); //withdraw / deposit: ammount
	amount = amount.substring(1, amount.length()-2);
	System.out.println(amount); //split into 2 things: string and integer
	String[] valstrg = amount.split(":");
	String type = "";
	for(int x = 0; x < valstrg[0].length(); x++) {
		if(Character.isAlphabetic(valstrg[0].charAt(x))) {
			type += valstrg[0].charAt(x);
		}
	}
	float val = Float.valueOf(valstrg[1]);
	
	
	as.ExchangeAccount(id, val, type, user_id);
}
public static void transferFunds(Context ctx) throws Exception {
	int id = Integer.parseInt(ctx.pathParam("AccountNum"));
	int user_id = Integer.parseInt(ctx.pathParam("id"));
	try {
		us.getUserById(user_id);
	} catch(Exception e){
		e.printStackTrace();
	}
	int id2 = Integer.parseInt(ctx.pathParam("funds"));
	String amount = ctx.body(); //split it up if it takes in the name too
	amount = amount.substring(1, amount.length()-2);
	String[] splt = amount.split(":");
	float transfer = Float.valueOf(splt[1]);
	System.out.println(transfer);
	as.transferFunds(id, user_id, id2, transfer);
	
} //do we have to do some sort of linking for each client in the api?
}
