package org.lessons.java.spring.pizzeria.repository;

import org.lessons.java.spring.pizzeria.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {
}
