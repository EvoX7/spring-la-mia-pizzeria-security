package org.pizzeria.italy.controller;

import java.util.List;
import org.pizzeria.italy.pojo.Pizza;
import org.pizzeria.italy.pojo.Promotion;
import org.pizzeria.italy.service.PizzaService;
import org.pizzeria.italy.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promotion")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	@Autowired
	public PizzaService pizzaService;

	@GetMapping
	public String getPromotions(Model model) {

		List<Promotion> promotions = promotionService.findAllPizza();
		model.addAttribute("promotions", promotions);

		return "index-promotion";
	}

	@GetMapping("/admin/create")
	public String createNewPromotion(Model model) {

		Promotion promotion = new Promotion();
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("promotion", promotion);

		return "promotion-create";
	}

	@PostMapping("/admin/create")
	public String storePromotion(@Valid Promotion promotion, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {

			System.err.println("---------------------- START ERROR ----------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("---------------------- END ERROR ------------------------");

			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

			return "redirect:/promotion/create";
		}

		List<Pizza> promotionPizzas = promotion.getPizzas();
		for (Pizza pizza : promotionPizzas) {
			pizza.setPromotion(promotion);
		}
		promotionService.save(promotion);

		return "redirect:/promotion";
	}

	@GetMapping("/admin/delete/{id}")
	public String deletePromotion(@PathVariable("id") int id) {

		promotionService.deletePromotionById(id);

		return "redirect:/promotion";
	}
}