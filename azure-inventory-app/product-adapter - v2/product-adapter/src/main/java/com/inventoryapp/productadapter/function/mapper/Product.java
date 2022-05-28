package com.inventoryapp.productadapter.function.mapper;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class Product implements Function<Mono<String>, Mono<String>> {

    public Mono<String> apply(Mono<String> mono) {
        return mono.map(user -> "Hello, " + user + "!\n");
    }
}