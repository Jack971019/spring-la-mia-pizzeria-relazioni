package org.lessons.java.spring.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.lessons.java.spring.pizzeria.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
