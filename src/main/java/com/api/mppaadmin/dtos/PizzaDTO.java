package com.api.mppaadmin.dtos;

import javax.validation.constraints.NotBlank;

public class PizzaDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
