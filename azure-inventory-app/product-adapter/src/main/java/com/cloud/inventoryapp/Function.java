package com.cloud.inventoryapp;

import com.cloud.inventoryapp.client.ProductClient;
import com.cloud.inventoryapp.client.ProductClientImpl;
import com.cloud.inventoryapp.dto.ProductDto;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {

    @FunctionName("HttpExample")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        final String id = request.getQueryParameters().get("id");

        if (id != null) {
            ProductClient productClient = new ProductClientImpl(context);
            ProductDto productDto = productClient.getProductById(id);
            
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(productDto).build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body("No id provided").build();
        }
    }
}
