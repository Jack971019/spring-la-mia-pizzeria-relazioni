package org.lessons.java.spring.pizzeria.repository;

import org.lessons.java.spring.pizzeria.model.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialOffersRepository extends JpaRepository<SpecialOffer, Integer> {

}
