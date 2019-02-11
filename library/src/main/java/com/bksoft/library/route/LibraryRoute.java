/**
 * 
 */
package com.bksoft.library.route;

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
	public RouterFunction<ServerResponse> bookRoute(LibraryHandler libraryHandler) {

		return RouterFunctions.route(RequestPredicates.GET("/book").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
				libraryHandler::getBookByAuthor);
	}

	@Bean
	public RouterFunction<ServerResponse> route(LibraryHandler libraryHandler) {

		return RouterFunctions.route(RequestPredicates.GET("/bookbytitle").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
				libraryHandler::getBookByTitle);
	}	
}
