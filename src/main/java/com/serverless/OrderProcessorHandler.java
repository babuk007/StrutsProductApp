package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class OrderProcessorHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        response.setHeaders(headers);

        try {
            // Extract the JSON body from the APIGatewayProxyRequestEvent
            String requestBody = request.getBody();
            context.getLogger().log("Received request body: " + requestBody);

            // Parse the request body into our OrderRequest POJO
            OrderRequest orderRequest = gson.fromJson(requestBody, OrderRequest.class);

            if (orderRequest == null || orderRequest.getOrderId() == null || orderRequest.getCustomerId() == null) {
                response.setStatusCode(400);
                response.setBody(gson.toJson(new ErrorResponse("Missing required order parameters")));
                return response;
            }

            // Original business logic
            String orderId = orderRequest.getOrderId();
            double amount = orderRequest.getAmount();
            String customerId = orderRequest.getCustomerId();

            context.getLogger().log("Processing order: " + orderId + " for amount " + amount + " for customer: " + customerId);

            // Prepare the response
            OrderResponse orderResponse = new OrderResponse("processed", orderId);
            response.setStatusCode(200);
            response.setBody(gson.toJson(orderResponse));

        } catch (Exception e) {
            context.getLogger().log("Error processing order: " + e.getMessage());
            response.setStatusCode(500);
            response.setBody(gson.toJson(new ErrorResponse("Internal server error: " + e.getMessage())));
        }
        return response;
    }
}

// POJO to represent the incoming order request payload
class OrderRequest {
    private String orderId;
    private double amount;
    private String customerId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

// POJO to represent the outgoing order response payload
class OrderResponse {
    private String status;
    private String orderId;

    public OrderResponse(String status, String orderId) {
        this.status = status;
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

// POJO for error responses
class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}