package com.api.mppaadmin.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mppaadmin.dtos.PizzaDTO;
import com.api.mppaadmin.models.PizzaModel;
import com.api.mppaadmin.services.PizzaService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/pizzas")
public class PizzaController {
    final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    public ResponseEntity<Object> savePizza(@RequestBody @Valid PizzaDTO pizzaDTO) {
        if (pizzaService.existsByTitle(pizzaDTO.getTitle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Title already exists.");
        }
        if (pizzaService.existsByDescription(pizzaDTO.getDescription())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Description already exists.");
        }

        var pizzaModel = new PizzaModel();
        BeanUtils.copyProperties(pizzaDTO, pizzaModel);
        pizzaModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(pizzaService.save(pizzaModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAllPizzas() {
        return ResponseEntity.status(HttpStatus.OK).body(pizzaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePizza(@PathVariable(value = "id") UUID id) {
        Optional<PizzaModel> pizzaModelOptional = pizzaService.findById(id);

        if (!pizzaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id.toString() + " not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pizzaModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOnePizza(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid PizzaDTO pizzaDTO) {
        Optional<PizzaModel> pizzaModelOptional = pizzaService.findById(id);
        if (!pizzaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id.toString() + " not found.");
        }

        PizzaModel pizzaModel = pizzaModelOptional.get();
        pizzaModel.setTitle(pizzaDTO.getTitle());
        pizzaModel.setDescription(pizzaDTO.getDescription());
        pizzaModel.setPrice(pizzaDTO.getPrice());

        return ResponseEntity.status(HttpStatus.OK).body(pizzaService.save(pizzaModel));
    }

}
