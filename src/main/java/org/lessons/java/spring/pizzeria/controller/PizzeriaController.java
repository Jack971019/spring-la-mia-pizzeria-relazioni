package org.lessons.java.spring.pizzeria.controller;

import org.lessons.java.spring.pizzeria.model.Pizza;
import org.lessons.java.spring.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class PizzeriaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String pizzeria(Model model){

        List<Pizza> pizzaList = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzaList);
        return "pizzaList";
    }
}
