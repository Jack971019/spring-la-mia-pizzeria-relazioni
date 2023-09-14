package org.lessons.java.spring.pizzeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PizzeriaController {

    @GetMapping
    public String pizzeria(Model model){
        return "piizaList";
    }
}
