package com.inventoryapp.productadapter.function;

import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

public class ProductHandler extends FunctionInvoker<String, String> {

    @FunctionName("product")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = { HttpMethod.GET,
                    HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            ExecutionContext context) {
        /*
         * User user = request.getBody()
         * .filter((u -> u.getName() != null))
         * .orElseGet(() -> new User(
         * request.getQueryParameters()
         * .getOrDefault("name", "world")));
         */
        final String name = request.getQueryParameters().get("name");

        context.getLogger().info("Greeting user name: " + name);
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(name, context))
                .header("Content-Type", "application/json")
                .build();
    }
}