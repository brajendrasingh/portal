/**
 * 
 */
package com.bksoft.library.route;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bksoft.library.handler.LibraryHandler;

/**
 * @author BRAJENDRA SINGH
 *
 */
@Configuration
public class LibraryRoute {

	@Bean
	public RouterFunction<ServerResponse> addBookRoute(LibraryHandler libraryHandler) {

		return route(POST("/addbook").and(accept(MediaType.APPLICATION_JSON)), libraryHandler::addBook);
	}

	@Bean
	public RouterFunction<ServerResponse> addBooksRoute(LibraryHandler libraryHandler) {

		return route(POST("/addbooks").and(accept(MediaType.APPLICATION_JSON)), libraryHandler::addBooks);
	}

	@Bean
	public RouterFunction<ServerResponse> routerUpdateBook(LibraryHandler libraryHandler) {

		return route(POST("/updatebook").and(accept(MediaType.APPLICATION_JSON)), libraryHandler::updateBook);
	}

	@Bean
	public RouterFunction<ServerResponse> routeByTitle(LibraryHandler libraryHandler) {

		return RouterFunctions.route(
				RequestPredicates.GET("/bookbytitle").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
				libraryHandler::getBookByTitle);
	}

	@Bean
	public RouterFunction<ServerResponse> routerByPublisher(LibraryHandler libraryHandler) {
		return RouterFunctions.route(GET("/bookByPublisher"), libraryHandler::getBookByPublisher);
	}

	@Bean
	public RouterFunction<ServerResponse> routerBookByAuthor(LibraryHandler libraryHandler) {
		return RouterFunctions.route(GET("/bookbyauthor/{author}"), libraryHandler::getBookByAuthor);
	}

	@Bean
	public RouterFunction<ServerResponse> routerBookList(LibraryHandler libraryHandler) {
		return RouterFunctions.route(GET("/list"), libraryHandler::getAllBooks);
	}
}
