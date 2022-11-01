package com.api.mppaadmin.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.mppaadmin.models.PizzaModel;

@Repository
public interface PizzaRepository extends JpaRepository<PizzaModel, UUID> {
    boolean existsByTitle(String title);

    boolean existsByDescription(String description);
}
