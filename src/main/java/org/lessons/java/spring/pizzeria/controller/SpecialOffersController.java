package org.lessons.java.spring.pizzeria.controller;

import org.lessons.java.spring.pizzeria.model.Pizza;
import org.lessons.java.spring.pizzeria.model.SpecialOffer;
import org.lessons.java.spring.pizzeria.repository.PizzaRepository;
import org.lessons.java.spring.pizzeria.repository.SpecialOffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/specialOffers")
public class SpecialOffersController {

    @Autowired
    private SpecialOffersRepository specialOfferRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/create")
    public String create(@RequestParam("pizzaId") Integer pizzaId, Model model) {
        Optional<Pizza> pizzaResult = pizzaRepository.findById(pizzaId);
        if (pizzaResult.isPresent()) {
            Pizza pizza = pizzaResult.get();
            SpecialOffer specialOffer = new SpecialOffer();
            specialOffer.setPizza(pizza);
            specialOffer.setStartDate(LocalDate.now());
            specialOffer.setExpireDate(LocalDate.now().plusDays(30));
            model.addAttribute("specialOffer", specialOffer);
            return "specialOffers/form";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute("specialOffer") SpecialOffer specialOfferForm) {
        specialOfferRepository.save(specialOfferForm);
        return "redirect:/show/" + specialOfferForm.getPizza().getId();
    }

    @GetMapping("/edit/{specialofferId}")
    public String edit(@PathVariable("specialOfferId") Integer id, Model model) {
        Optional<SpecialOffer> specialOfferResult = specialOfferRepository.findById(id);
        if (specialOfferResult.isPresent()) {
            model.addAttribute("specialOffer", specialOfferResult.get());
            return "specialOffers/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/edit/{specialOfferId}")
    public String doEdit(@PathVariable("specialOfferId") Integer specialOfferId,
                         @ModelAttribute("specialOffer") SpecialOffer specialOfferForm) {
        specialOfferForm.setId(specialOfferId);
        specialOfferRepository.save(specialOfferForm);
        return "redirect:/show/" + specialOfferForm.getPizza().getId();
    }

    @PostMapping("/delete/{specialOfferId}")
    public String delete(@PathVariable("specialOfferId") Integer id) {
        Optional<SpecialOffer> specialOfferResult = specialOfferRepository.findById(id);
        if (specialOfferResult.isPresent()) {
            Integer pizzaId = specialOfferResult.get().getPizza().getId();
            specialOfferRepository.deleteById(id);
            return "redirect:/show/" + pizzaId;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
