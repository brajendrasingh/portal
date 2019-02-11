/**
 * 
 */
package com.bksoft.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bksoft.library.model.Book;

/**
 * @author BRAJENDRA SINGH
 *
 */
@Component
public class BookService {

	public void addBook(Book book) {
	}

	public Book getBookByTitle(String title) {
		Book book = new Book("Java Programming", "BKSOFT", 2018, 55.21, "Indian");
		return book;
	}

	public List<Book> getBookByPublisher(String publisher) {
		Book book = new Book("C Programming", "BKSOFT", 2019, 85.99, "Indian");
		List<Book> list = new ArrayList<Book>();
		list.add(book);
		return list;
	}

	public List<Book> getBookByAuthor(String author) {
		Book book = new Book("C++ Programming", "BKSOFT", 2017, 155.52, "Indian");
		List<Book> list = new ArrayList<Book>();
		list.add(book);
		return list;

	}

}
