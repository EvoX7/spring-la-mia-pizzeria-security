package org.pizzeria.italy.controller;

import java.util.List;
import java.util.Optional;
import org.pizzeria.italy.pojo.Drink;
import org.pizzeria.italy.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/drink")
public class DrinkController {

	@Autowired
	public DrinkService drinkService;

	@GetMapping
	public String getDrinks(Model model) {

		List<Drink> drinks = drinkService.findAll();
		model.addAttribute("drinks", drinks);

		return "index2";
	}

	@GetMapping("/admin/create")
	public String createNewDrink(Model model) {

		Drink drink = new Drink();
		model.addAttribute("drink", drink);

		return "drink-create";
	}

	@PostMapping("/admin/create")
	public String getStoreDrink(@Valid Drink drink, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {

			System.err.println("---------------------- START ERROR ----------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("---------------------- END ERROR ------------------------");

			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

			return "redirect:/drink/create";
		}

		drinkService.save(drink);

		return "redirect:/drink";
	}

	@GetMapping("/admin/edit/{id}")
	public String editDrink(@PathVariable("id") int id, Model model) {

		Optional<Drink> optDrink = drinkService.findDrinkById(id);
		Drink drink = optDrink.get();

		model.addAttribute("drink", drink);

		return "drink-update";
	}

	@PostMapping("/admin/edit")
	public String updateDrink(@Valid Drink drink) {

		drinkService.save(drink);

		return "redirect:/drink";
	}

	@GetMapping("/admin/delete/{id}")
	public String deleteDrink(@PathVariable("id") int id) {

		drinkService.deleteById(id);

		return "redirect:/drink";
	}

	@GetMapping("/user/search")
	public String getSearchDrinkByName(Model model, @RequestParam(name = "query", required = false) String query) {

		List<Drink> drinks = null;
		if (query == null) {

			drinks = drinkService.findAll();

		} else {

			drinks = drinkService.findByName(query);
		}

		model.addAttribute("drinks", drinks);
		model.addAttribute("query", query);

		return "drink-search";
	}
}
