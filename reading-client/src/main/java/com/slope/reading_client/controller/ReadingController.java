package com.slope.reading_client.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slope.reading_client.service.IReadService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/read")
public class ReadingController {

    private final IReadService readService;

    @GetMapping()
    public Mono<List<Map<String, String>>> getReadingList() {
        return readService.getRecommendedBooks();
    }
}
