/**
 * 
 */
package com.bksoft.library.handler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bksoft.library.model.Book;
import com.bksoft.library.service.BookService;

import reactor.core.publisher.Mono;

/**
 * @author BRAJENDRA SINGH
 *
 */
@Component
public class LibraryHandler {

	@Autowired
	private BookService bookService;

	public Mono<ServerResponse> getBookByTitle(ServerRequest request) {

		Optional<String> opt = request.queryParam("title");
		String title = opt.get();
		Book b = bookService.getBookByTitle(title);

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(b));
	}

	public Mono<ServerResponse> getBookByPublisher(ServerRequest request) {

		Optional<String> opt = request.queryParam("publisher");

		String publisher = opt.get();

		Book b = bookService.getBookByPublisher(publisher).get(0);

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(b));
	}

	public Mono<ServerResponse> getBookByAuthor(ServerRequest request) {

		String author = String.valueOf(request.pathVariable("author"));

		Book b = bookService.getBookByAuthor(author).get(0);

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(b));
	}

	public Mono<ServerResponse> addBook(ServerRequest request) {

		Book book = null;
		boolean success = false;
		try {
			book = request.bodyToMono(Book.class).toProcessor().peek();
			success = bookService.addBook(book);
		} catch (Exception e) {
			System.out.println(e);
		}
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(success));
	}

	public Mono<ServerResponse> addBooks(ServerRequest request) {

		Book books[] = null;
		try {
			books = request.bodyToMono(Book[].class).toProcessor().peek();
			bookService.addBooks(books);
		} catch (Exception e) {
			System.out.println(e);
		}
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(books));
	}

	public Mono<ServerResponse> updateBook(ServerRequest request) {

		Book books[] = null;
		try {
			books = request.bodyToMono(Book[].class).toProcessor().peek();
			bookService.addBooks(books);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(books));
	}

	public Mono<ServerResponse> getAllBooks(ServerRequest request) {
		List<Book> bList = bookService.getAllBooks();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(bList));
	}

}