package com.cloud.inventoryapp.client;

import com.cloud.inventoryapp.dto.ProductDto;
import com.microsoft.azure.functions.ExecutionContext;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ProductClientImpl implements ProductClient {
    ExecutionContext context;

    public ProductClientImpl(ExecutionContext context) {
        this.context = context;
    }

    @Override
    public ProductDto getProductById(String id) {
        String url =  "https://fakestoreapi.com/products/" + id;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        context.getLogger().info("About to send request to Fake Store API");
        ResponseEntity<ProductDto> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), ProductDto.class);
        context.getLogger().info("Response from Fake Store API: " + response.getBody());

        return response.getBody();
    }

}
