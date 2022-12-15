package org.pizzeria.italy.repo;

import java.util.List;

import org.pizzeria.italy.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {
	
	List<Pizza> findByNameContainingIgnoreCase(String name);

}


	  