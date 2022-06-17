package dev.rowand.service;

import java.util.List;

import dev.rowand.model.Account;
import dev.rowand.model.Client;
import dev.rowand.repo.AccountDAO;
import io.javalin.http.Context;

public class AccountService {
	private AccountDAO AccountDao = new AccountDAO();



public List<Account> getAllAccounts(int user_id, String query) {
	
	return AccountDao.getAllAccounts(user_id, query);
}
public Account createAccount(Account a, int user_id) {
	Account createdAccount = AccountDao.createAccount(a, user_id);
	return createdAccount;
}
public Account getAccountById(int id, int user_id) throws Exception{
Account a = AccountDao.getAccountById(id, user_id);
if(a == null) { //add exceptions like this for all 404 messages that can exist
	throw new Exception ("Account not found!");
}
return a;
}
public void updateAccount(Account aChanged) throws Exception {

Account a = AccountDao.getAccountById(aChanged.getId(), aChanged.getUserId());
if(a == null) { //account user_id and account id not found
	throw new Exception("Client not found!");
}
AccountDao.updateAccount(aChanged);
}
public void deleteAccount(int id, int user_id) throws Exception {

Account a = AccountDao.getAccountById(id, user_id);
if(a == null) {
	throw new Exception("Account not found!");
}
AccountDao.deleteAccount(id, user_id);
}
public void ExchangeAccount(int id, float balancechng, String type, int user_id) throws Exception {
	Account a = AccountDao.getAccountById(id, user_id);
	if(a == null) { //add exceptions like this for all 404 messages that can exist
		throw new Exception ("Account not found!");
	}
	System.out.println(a.getBalance());
	System.out.println(balancechng);
	System.out.println(type);
	float bal = 0;
	if(type.equals("deposit") ) {
		bal = a.getBalance() + balancechng;
		
	}
	else if(type.equals("withdraw")) {
		bal= a.getBalance() - balancechng; 
		System.out.println(balancechng);
	}
	if(bal < 0) {
		throw new Exception ("insufficient funds");
	}
	System.out.println(bal + " is the current balance");
AccountDao.ExchangeAccount( bal, a);
}
public  void transferFunds(int id, int user_id, int id2, float transfer) throws Exception {
Account a = AccountDao.getAccountById(id, user_id);
if(a == null) { //add exceptions like this for all 404 messages that can exist
	throw new Exception ("Account not found!");
}
Account b = AccountDao.getAccountById(id2, user_id);
if(b == null) { //add exceptions like this for all 404 messages that can exist
	throw new Exception ("Account not found!");
}
Float aBalance = a.getBalance() - transfer;
if(aBalance < 0) {
	throw new Exception ("insufficient funds");
}
Float bBalance = b.getBalance() + transfer;
AccountDao.transferFunds(a, b, aBalance, bBalance);
//put in the amount to be transfered here, as well as add and subtract the balances of each other accounts
}

}