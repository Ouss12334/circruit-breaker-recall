package com.slope.bookstore_service.service;

import java.util.List;

import com.slope.bookstore_service.data.Book;

import reactor.core.publisher.Mono;

public interface IBookService {

    /**
     * get the list of books
     */
    Mono<List<Book>> getBooks();
}
