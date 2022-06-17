package dev.rowand.model;

public class Book {
private int id;
private String name;
private String Author;
@Override
public String toString() {
	return "Book [id=" + id + ", name=" + name + ", Author=" + Author + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAuthor() {
	return Author;
}
public void setAuthor(String author) {
	Author = author;
}
}
