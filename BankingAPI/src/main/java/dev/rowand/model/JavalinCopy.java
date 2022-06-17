package dev.rowand.model;
import dev.rowand.controller.AccountController;
import dev.rowand.controller.UserController;

import java.nio.file.Path;
import java.util.List;

import dev.rowand.controller.UserController;
import dev.rowand.repo.UserDAO;
import dev.rowand.service.UserService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;
//was heavily changed in Dan's repo. Look over it all
public class JavalinCopy {
	private static UserService userService;
	
	public static void main(String[] args) {
		
		userService = new UserService(new UserDAO());
		
		Javalin app = Javalin.create();
		
		app.start(8081);
		
		
		
		// Javalin provides us with a Context Class (ctx) that represents information 
		// about BOTH the Http Request AND Http Response Objects
		// we'll be using methods from the context class to handle our incoming http requests
		// and to send our http resonses
		
		//lambdas -introduced functional programming to Java
		//(parameter) -> { //implementation }
		
		app.routes(() ->{
			path("/clients", ()->{
				get(UserController::getAllUsers);
				post(UserController::createNewUser);
				path("/{id}", () ->{
					get(UserController::getUserById);
					delete(UserController::deleteUser);
					put(UserController::updateUser);
				    path("/accounts", () ->{ //ask about the accounts?amountLessThan=2000 thingie
				    	get(AccountController::getAllAccounts);
				    	post(AccountController::createNewAccount);
				    	path("/{AccountNum}", () ->{
				    		get(AccountController::getAccountById);
				    		put(AccountController::updateAccount);
				    		delete(AccountController::deleteAccount);
				    		patch(AccountController::ExchangeAccount);
				    		path("/transfer", () ->{
				    			path("/{funds}", () ->{
				    				patch(AccountController::transferFunds);
				    			});
				    		
				    		});
				    		
				    	});
				    });
	
				});
			});
		});
		
		app.exception(Exception.class, (e, ctx) ->{
			ctx.status(404);
			ctx.result("<h1>Client not found!</h1>");
		}); 
		
		
		
		
		
		
		
		app.get("/test", ctx -> {
			ctx.status(200);
			String name = ctx.queryParam("name");
			ctx.result("Test successful!");
		});
		
		
		app.get("/{name}", ctx -> {
			ctx.status(200);
			String name = ctx.pathParam("name");
			ctx.result("Hello, " + name);
		});
		
		
		
		
	}
}

