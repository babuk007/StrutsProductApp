package com.legacy.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderController {
    public void processOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderId = request.getParameter("orderId");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String customerId = request.getParameter("customerId");
        
        System.out.println("Processing order: " + orderId + " for amount " + amount);
        response.setStatus(200);
        response.getWriter().write("{\"status\": \"processed\", \"orderId\": \"" + orderId + "\" }");
    }
}