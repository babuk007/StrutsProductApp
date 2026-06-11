package com.example.action;

import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport {
    private String name;
    private double price;
    private String description;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // Action method
    @Override
    public String execute() {
        // Hardcoded product info (can later be fetched from DB)
        name = "Struts Book";
        price = 49.99;
        description = "A complete guide to Struts framework.";
        return SUCCESS;
    }
}
