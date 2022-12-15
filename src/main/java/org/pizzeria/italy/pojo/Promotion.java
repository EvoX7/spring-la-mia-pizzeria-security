package org.pizzeria.italy.pojo;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Title cannot be blank")
	@Column(unique = true)
	private String title;

	@NotNull(message = "There must be a date")
	@Column
	private LocalDate initialDate;

	@NotNull(message = "There must be a date")
	@Column
	private LocalDate endDate;
	
	@OneToMany(mappedBy = "promotion", cascade = CascadeType.REMOVE)
	private List<Pizza> pizzas;

	public Promotion() {
	}

	public Promotion(String title, LocalDate initialDate, LocalDate endDate) {

		setTitle(title);
		setInitialDate(initialDate);
		setEndDate(endDate);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(LocalDate initialDate) {
		this.initialDate = initialDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	@Override
	public String toString() {
		return "(" + getId() + ")" + " " + getTitle() + " - " + getInitialDate() + " " + getEndDate();
	}

	

}
