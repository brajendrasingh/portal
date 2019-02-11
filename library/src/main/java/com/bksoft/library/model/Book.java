/**
 * 
 */
package com.bksoft.library.model;

/**
 * @author BRAJENDRA SINGH
 *
 */
public class Book {

	String title;
	String author;
	int year;
	double price;
	String publisher;

	public Book() {
	}

	public Book(String title, String author, int year, double price, String publisher) {
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + ", price=" + price + ", publisher="
				+ publisher + "]";
	}

}
