/**
 * 
 */
package com.bksoft.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.bksoft.library.model.Book;

/**
 * @author BRAJENDRA SINGH
 *
 */
@Component
public class BookService {

	WebClient webClient = WebClient.builder().baseUrl("http://localhos:8094")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
			.defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient").build();

	public boolean addBook(Book book) {
		System.out.println(book);
		return true;
	}

	public boolean addBooks(Book[] book) {
		System.out.println(book);
		return true;
	}

	public Book updateBook(Book book) {
		Book b = new Book(book.getTitle(), "BKSOFT", 2018, 55.21, "Indian");
		return b;
	}

	public Book getBookByTitle(String title) {
		Book book = new Book(title + " Programming", "BKSOFT", 2018, 55.21, "Indian");
		return book;
	}

	public List<Book> getBookByPublisher(String publisher) {
		Book book = new Book("C Programming", "BKSOFT", 2019, 85.99, publisher + " India");
		List<Book> list = new ArrayList<Book>();
		list.add(book);
		return list;
	}

	public List<Book> getBookByAuthor(String author) {
		Book book = new Book("C++ Programming", author + " BKSOFT", 2017, 155.52, "Indian");
		List<Book> list = new ArrayList<Book>();
		list.add(book);
		return list;

	}

	public List<Book> getAllBooks() {
		Book book = new Book("C++ Programming", "BKSOFT", 2017, 155.52, "Indian");
		List<Book> list = new ArrayList<Book>();
		list.add(book);
		list.add(book);
		list.add(book);
		list.add(book);
		list.add(book);
		return list;

	}

}
