package com.bajkic.budgetApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Expenses")
public class Expense {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String expenseName;
	private String expenseCoverage;
	private double expenseCost;
	
	
	
	public Expense() {
		super();
	}
	public Expense(Integer id, String expenseName, String expenseCoverage, double expenseCost) {
		super();
		this.id = id;
		this.expenseName = expenseName;
		this.expenseCoverage = expenseCoverage;
		this.expenseCost = expenseCost;
	}
	
	
	
	public Expense(String expenseName, String expenseCoverage, double expenseCost) {
		super();
		this.expenseName = expenseName;
		this.expenseCoverage = expenseCoverage;
		this.expenseCost = expenseCost;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public String getExpenseCoverage() {
		return expenseCoverage;
	}
	public void setExpenseCoverage(String expenseCoverage) {
		this.expenseCoverage = expenseCoverage;
	}
	public double getExpenseCost() {
		return expenseCost;
	}
	public void setExpenseCost(double expenseCost) {
		this.expenseCost = expenseCost;
	}
	
	
	
}
