package dev.rowand.model;

public class Account {
private String type;
private float balance;
private int id;
private int user_id; //switch this and all things based on it back to int if things get messy

public Account(int id, String type, float balance, int user_id) {
	// TODO Auto-generated constructor stub
	this.id = id;
	this.type = type;
	this.balance = balance;
	this.user_id = user_id;
}
public Account() {}

public float getBalance() {
	return balance;
}

public void setBalance(float balance) {
	this.balance = balance;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public int getUserId() {
	return user_id;
}

public void setUserId(int user_id) {
	this.user_id = user_id;
}



}
