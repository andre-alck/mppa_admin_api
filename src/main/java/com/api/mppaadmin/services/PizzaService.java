package com.api.mppaadmin.services;

import org.springframework.stereotype.Service;

import com.api.mppaadmin.repositories.PizzaRepository;

@Service
public class PizzaService {
    final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }
}
