package org.pizzeria.italy.repo;

import java.util.List;

import org.pizzeria.italy.pojo.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepo extends JpaRepository<Drink, Integer> {

	List<Drink> findByNameContainingIgnoreCase(String name);

}
