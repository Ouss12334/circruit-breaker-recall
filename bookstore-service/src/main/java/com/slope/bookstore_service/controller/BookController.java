package com.slope.bookstore_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slope.bookstore_service.data.Book;
import com.slope.bookstore_service.service.IBookService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @GetMapping("/recommended")
    public Mono<List<Book>> readingList() {
        return bookService.getBooks();
    }
    
}
