package com.cloud.inventoryapp.client;

import com.cloud.inventoryapp.dto.ProductDto;

public interface ProductClient {

    ProductDto getProductById(String id);
}