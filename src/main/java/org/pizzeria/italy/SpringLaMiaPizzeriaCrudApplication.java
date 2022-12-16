package org.pizzeria.italy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pizzeria.italy.pojo.Drink;
import org.pizzeria.italy.pojo.Ingredient;
import org.pizzeria.italy.pojo.Pizza;
import org.pizzeria.italy.pojo.Promotion;
import org.pizzeria.italy.pojo.Role;
import org.pizzeria.italy.pojo.User;
import org.pizzeria.italy.service.PizzaService;
import org.pizzeria.italy.service.PromotionService;
import org.pizzeria.italy.service.RoleService;
import org.pizzeria.italy.service.UserService;
import org.pizzeria.italy.service.DrinkService;
import org.pizzeria.italy.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private DrinkService drinkService;

	@Autowired
	private PromotionService promotionService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Ingredients

		Ingredient ing1 = new Ingredient("tomato");
		Ingredient ing2 = new Ingredient("mozzarella");
		Ingredient ing3 = new Ingredient("olive oil");
		Ingredient ing4 = new Ingredient("basil");
		Ingredient ing5 = new Ingredient("salami");
		Ingredient ing6 = new Ingredient("wurstel");
		Ingredient ing7 = new Ingredient("ham");
		Ingredient ing8 = new Ingredient("cheese");
		Ingredient ing9 = new Ingredient("french fries");

		System.out.println(ing1);
		System.out.println(ing2);
		System.out.println(ing3);
		System.out.println(ing4);
		System.out.println(ing5);
		System.out.println(ing6);
		System.out.println(ing7);
		System.out.println(ing8);
		System.out.println(ing9);

		List<Ingredient> ingredientsList1 = new ArrayList<>();
		ingredientsList1.add(ing1);
		ingredientsList1.add(ing2);
		ingredientsList1.add(ing3);
		ingredientsList1.add(ing4);

		List<Ingredient> ingredientsList2 = new ArrayList<>();
		ingredientsList2.add(ing1);
		ingredientsList2.add(ing8);
		ingredientsList2.add(ing4);

		List<Ingredient> ingredientsList3 = new ArrayList<>();
		ingredientsList3.add(ing1);
		ingredientsList3.add(ing4);

		List<Ingredient> ingredientsList4 = new ArrayList<>();
		ingredientsList4.add(ing6);
		ingredientsList4.add(ing9);

		List<Ingredient> ingredientsList5 = new ArrayList<>();
		ingredientsList5.add(ing1);
		ingredientsList5.add(ing3);
		ingredientsList5.add(ing5);

		List<Ingredient> ingredientsList6 = new ArrayList<>();
		ingredientsList6.add(ing2);
		ingredientsList6.add(ing7);

		List<Ingredient> ingredientsList7 = new ArrayList<>();
		ingredientsList7.add(ing1);
		ingredientsList7.add(ing3);
		ingredientsList7.add(ing4);

		List<Ingredient> ingredientsList8 = new ArrayList<>();
		ingredientsList8.add(ing1);
		ingredientsList8.add(ing2);
		ingredientsList8.add(ing4);
		ingredientsList8.add(ing5);

		ingredientService.save(ing1);
		ingredientService.save(ing2);
		ingredientService.save(ing3);
		ingredientService.save(ing4);
		ingredientService.save(ing5);
		ingredientService.save(ing6);
		ingredientService.save(ing7);
		ingredientService.save(ing8);
		ingredientService.save(ing9);

//		Promotions

		Promotion prom1 = new Promotion("Super promotion", LocalDate.parse("2022-06-20"),
				LocalDate.parse("2022-06-23"));
		Promotion prom2 = new Promotion("Back to pizza", LocalDate.parse("2022-07-15"), LocalDate.parse("2022-07-25"));
		Promotion prom3 = new Promotion("Save the pizza", LocalDate.parse("2022-08-10"), LocalDate.parse("2022-08-14"));

		promotionService.save(prom1);
		promotionService.save(prom2);
		promotionService.save(prom3);

		System.out.println(prom1);
		System.out.println(prom2);
		System.out.println(prom3);

//		Pizzas

		Pizza p1 = new Pizza("Margherita", "Pizza classica", 7, prom1, ingredientsList1);
		Pizza p2 = new Pizza("4 formaggi", "La più buona", 10, prom1, ingredientsList2);
		Pizza p3 = new Pizza("Napoletana", "Per gli amanti", 8, prom2, ingredientsList3);
		Pizza p4 = new Pizza("Calzone", "Ripieno classico", 9, prom1, ingredientsList6);
		Pizza p5 = new Pizza("Bismark", "La tedesca", 11, prom3, ingredientsList4);
		Pizza p6 = new Pizza("Marinara", "La semplice", 4, prom2, ingredientsList7);
		Pizza p7 = new Pizza("Diavola", "La piccante", 7, prom3, ingredientsList5);
		Pizza p8 = new Pizza("Siciliana", "L'isolana", 7, prom1, ingredientsList8);
		Pizza p9 = new Pizza("Boscaiola", "Quella rude", 8, prom3, ingredientsList8);
		Pizza p10 = new Pizza("Prosciutto", "Quello cotto", 8, prom2, ingredientsList6);

		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		pizzaService.save(p5);
		pizzaService.save(p6);
		pizzaService.save(p7);
		pizzaService.save(p8);
		pizzaService.save(p9);
		pizzaService.save(p10);

//		Drinks

		Drink d1 = new Drink("Negroni", "Un classico", 7);
		Drink d2 = new Drink("Manhattan", "Quello glamour", 10);
		Drink d3 = new Drink("Moscow Mule", "Per i temerari", 8);
		Drink d4 = new Drink("Mojito", "Aria esotica", 7);
		Drink d5 = new Drink("Gin Tonic", "Leggero", 4);
		Drink d6 = new Drink("Blue lagoon", "Azzurro", 7);
		Drink d7 = new Drink("Pina colada", "Spiagga esotica", 10);
		Drink d8 = new Drink("Cosmopolitan", "Internazionale", 8);
		Drink d9 = new Drink("Mulled vine", "Quello ricco", 7);
		Drink d10 = new Drink("Bloody Mary", "Quello strano", 4);

		drinkService.save(d1);
		drinkService.save(d2);
		drinkService.save(d3);
		drinkService.save(d4);
		drinkService.save(d5);
		drinkService.save(d6);
		drinkService.save(d7);
		drinkService.save(d8);
		drinkService.save(d9);
		drinkService.save(d10);

//		Deleting promotions

//		promotionService.deletePromotionById(3);
//		System.out.println("---------------------------");

//		Pizza with promotions
		List<Pizza> pizzas = pizzaService.findAll();
		for (Pizza pizza : pizzas) {
			System.err.println(pizza + "\n\t" + pizza.getPromotion());
		}

		System.out.println("---------------------------");
		List<Promotion> promotions = promotionService.findAllPizza();

		for (Promotion promotion : promotions) {
			System.err.println(promotion);
			for (Pizza pizza : promotion.getPizzas()) {
				System.err.println("\t" + pizza);
			}
		}

//		Access roles 
		
		Role userRole = new Role("USER");
		Role adminRole = new Role("ADMIN");

		roleService.save(userRole);
		roleService.save(adminRole);

		User userUser = new User("user", "{noop}1234", userRole);
		User adminUser = new User("admin", "{noop}7890", adminRole);

		Set<Role> userAdminRoles = new HashSet<>();
		userAdminRoles.add(userRole);
		userAdminRoles.add(adminRole);
		User userAdminUser = new User("useradmin", "{noop}useradminpws", userAdminRoles);

		userService.save(userUser);
		userService.save(adminUser);
		userService.save(userAdminUser);
	}

}
