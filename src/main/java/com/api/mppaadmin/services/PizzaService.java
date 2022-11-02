package com.api.mppaadmin.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.mppaadmin.models.PizzaModel;
import com.api.mppaadmin.repositories.PizzaRepository;

@Service
public class PizzaService {
    final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Transactional
    public PizzaModel save(PizzaModel pizzaModel) {
        return pizzaRepository.save(pizzaModel);
    }

    public boolean existsByTitle(String title) {
        return pizzaRepository.existsByTitle(title);
    }

    public boolean existsByDescription(String description) {
        return pizzaRepository.existsByDescription(description);
    }

    public List<PizzaModel> findAll() {
        return pizzaRepository.findAll();
    }

    public Optional<PizzaModel> findById(UUID id) {
        return pizzaRepository.findById(id);
    }

    public void deleteById(UUID id) {
        pizzaRepository.deleteById(id);
    }

    public void deleteAll() {
        pizzaRepository.deleteAll();
    }
}
