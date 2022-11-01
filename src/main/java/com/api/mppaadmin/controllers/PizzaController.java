package com.api.mppaadmin.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
        return ResponseEntity.status(HttpStatus.OK).body(pizzaService.save(pizzaModel));
    }
}
