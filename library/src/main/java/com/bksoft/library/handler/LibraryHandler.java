/**
 * 
 */
package com.bksoft.library.handler;

import java.util.List;

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
		Book b = bookService.getBookByTitle("Java");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(b));
	}

	public Mono<ServerResponse> getBookByAuthor(ServerRequest request) {
		List<Book> bList = bookService.getBookByAuthor("Java");
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromObject("book"));
	}
}
