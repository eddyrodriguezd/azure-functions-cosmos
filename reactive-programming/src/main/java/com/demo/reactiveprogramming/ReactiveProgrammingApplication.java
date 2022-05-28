package com.demo.reactiveprogramming;

import com.demo.reactiveprogramming.document.Product;
import com.demo.reactiveprogramming.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class ReactiveProgrammingApplication /*implements CommandLineRunner*/ {

	private final ProductRepository productRepository;
	private final ReactiveMongoTemplate reactiveMongoTemplate;

	public ReactiveProgrammingApplication(ProductRepository productRepository, ReactiveMongoTemplate reactiveMongoTemplate) {
		this.productRepository = productRepository;
		this.reactiveMongoTemplate = reactiveMongoTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveProgrammingApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		reactiveMongoTemplate.dropCollection("products").subscribe();

		Flux.just(new Product("TV LCD", 4599.99),
				new Product("Apple iPod", 3500.00),
				new Product("HP Notebook", 2499.99),
				new Product("Sony Camara HD", 180.00))
				.flatMap(p -> {
					p.setCreatedAt(new Date());
					return productRepository.save(p);
				})
				.subscribe(p -> log.info("Product saved: " + p.getId()));
	}*/
}
