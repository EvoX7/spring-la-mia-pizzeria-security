package org.pizzeria.italy.controller;


import java.util.List;
import org.pizzeria.italy.pojo.Drink;
import org.pizzeria.italy.pojo.Pizza;
import org.pizzeria.italy.service.DrinkService;
import org.pizzeria.italy.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/globalsearch")
public class GlobalSearchController {
	

	@Autowired
	public PizzaService pizzaService;

	@Autowired
	public DrinkService drinkService;

//	SEARCH BAR 

	@GetMapping
	public String getGlobalSearchByName(Model model, @RequestParam(name = "query", required = false) String query) {

		List<Pizza> pizzas = null;
		List<Drink> drinks = null;

		if (query == null || query.isEmpty()) {

			pizzas = pizzaService.findAll();
			drinks = drinkService.findAll();

		} else {

			pizzas = pizzaService.findByName(query);
			drinks = drinkService.findByName(query);
		}

		model.addAttribute("pizzas", pizzas);
		model.addAttribute("drinks", drinks);

		model.addAttribute("query", query);

		return "global-search";
	}

	
}
