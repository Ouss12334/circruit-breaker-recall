package com.slope.reading_client.service;

import java.util.List;
import java.util.Map;

import reactor.core.publisher.Mono;

public interface IReadService {

    public Mono<List<Map<String, String>>> getRecommendedBooks();
}
