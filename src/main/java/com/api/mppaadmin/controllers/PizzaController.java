package com.api.mppaadmin.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mppaadmin.services.PizzaService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/pizzas")
public class PizzaController {
    final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
}
