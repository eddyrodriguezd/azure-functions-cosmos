package com.demo.reactiveprogramming.controller;

import com.demo.reactiveprogramming.document.Product;
import com.demo.reactiveprogramming.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/list")
    public Flux<Product>  getAll() {
        Flux<Product> productFlux = productRepository.findAll();
        productFlux.log().subscribe();
        return productFlux;
    }

    @GetMapping("/data-driven/list")
    public Flux<Product>  getAllDataDriven() {
        Flux<Product> productFlux = productRepository.findAll()
                .delayElements(Duration.ofSeconds(1));
        productFlux.log().subscribe();
        return productFlux;
    }

    @GetMapping("/list-full")
    public Flux<Product>  getAllFull() {
        Flux<Product> productFlux = productRepository.findAll()
                .repeat(5000);
        productFlux.log().subscribe();
        return productFlux;
    }
}
