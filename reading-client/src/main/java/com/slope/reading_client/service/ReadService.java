package com.slope.reading_client.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ReadService implements IReadService {

    private final WebClient webClient;
    private final ReactiveCircuitBreaker readingListCircuitBreaker;

    public ReadService(@SuppressWarnings("rawtypes") ReactiveCircuitBreakerFactory cbFactory) {
        this.webClient = WebClient.builder().build();
        this.readingListCircuitBreaker = cbFactory.create("recommended-books");
        
    }
    
    public Mono<List<Map<String, String>>> getRecommendedBooks() {
        log.info("fetching books");
        var apiResult = webClient
            .get()
            .uri("http://localhost:8084/books/recommended") // http:// is required when specifying port
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<Map<String, String>>>(){});
            // (instead of returning) read value of mono
            // apiResult.subscribe(
            //     v -> log.info("result from api {}", v) // value
            //     ,e -> log.info("error occured on get recommended with mono: {}", e.getMessage()) // error
            //     ,() -> log.info("completed without a value") // returned empty
            // );
        return readingListCircuitBreaker.run(apiResult, 
            throwable -> {
                log.info("error making request to books api => '{}'", throwable.getMessage());
                return Mono.just(List.of(Map.of("name", "best book 2K25")));
            });
    }

}
