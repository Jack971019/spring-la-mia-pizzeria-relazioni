package org.lessons.java.spring.pizzeria.controller;

import org.lessons.java.spring.pizzeria.model.Ingredient;
import org.lessons.java.spring.pizzeria.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    IngredientsRepository ingredientsRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredientList", ingredientsRepository.findAll());
        model.addAttribute("ingredientObj", new Ingredient());
        return "ingredients/index";
    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute("ingredientObj") Ingredient ingredientForm,
                           RedirectAttributes redirectAttributes) {
        ingredientsRepository.save(ingredientForm);
        redirectAttributes.addFlashAttribute("message", "ingredient successfully added");
        return "redirect:/ingredients";
    }
}
