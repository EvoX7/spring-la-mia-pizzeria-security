package org.pizzeria.italy.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.pizzeria.italy.pojo.Promotion;
import org.pizzeria.italy.repo.PromotionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PromotionService {

	@Autowired
	private PromotionRepo promotionRepo;

	public void save(Promotion promotion) {
		promotionRepo.save(promotion);
	}

	public List<Promotion> findAll() {
		return promotionRepo.findAll();
	}

	public Optional<Promotion> findPromotionById(int id) {

		return promotionRepo.findById(id);
	}

	public void deletePromotionById(int id) {
		promotionRepo.deleteById(id);
	}

	@Transactional
	public List<Promotion> findAllPizza() {
		List<Promotion> promotions = promotionRepo.findAll();

		for (Promotion promotion : promotions) {
			Hibernate.initialize(promotion.getPizzas());
		}

		return promotions;

	}
}
