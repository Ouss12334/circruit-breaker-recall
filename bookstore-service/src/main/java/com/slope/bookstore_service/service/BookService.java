package com.slope.bookstore_service.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.slope.bookstore_service.data.Book;

import reactor.core.publisher.Mono;

@Service
public class BookService implements IBookService {

    @Override
    public Mono<List<Book>> getBooks() {
        return Mono.just(Arrays.asList(
            new Book("Bunch of books")
            ,new Book("Spring in Action (Manning)")
            ,new Book("Cloud Native Java (O'Reilly)")
            ,new Book("Learning Spring Boot (Packt)")
        ));
    }

}
