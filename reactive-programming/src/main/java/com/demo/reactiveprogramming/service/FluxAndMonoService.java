package com.demo.reactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoService {

    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .map(String::toUpperCase)
                .doOnNext(e -> System.out.println(e));
    }

    public Mono<String> fruitsMono() {
        return Mono.just("Mango").log();
    }

    public static void main(String[] args) {
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();

        fluxAndMonoService.fruitsFlux().subscribe(s -> System.out.println("f= " + s));

        fluxAndMonoService.fruitsFluxMap().subscribe(s -> System.out.println("f= " + s));

        fluxAndMonoService.fruitsMono().subscribe(s -> System.out.println("m= " + s));
    }
}
