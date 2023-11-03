package com.bajkic.budgetApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sourcesOfIncome")
public class Income {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String sourceName;
	private double sourceValue;
	
	
	
	public Income(Integer id, String sourceName, double sourceValue) {
		super();
		this.id = id;
		this.sourceName = sourceName;
		this.sourceValue = sourceValue;
	}
	
	
	
	public Income(String sourceName, double sourceValue) {
		super();
		this.sourceName = sourceName;
		this.sourceValue = sourceValue;
	}



	public Income() {
		super();
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public double getSourceValue() {
		return sourceValue;
	}
	public void setSourceValue(double sourceValue) {
		this.sourceValue = sourceValue;
	}
	
	
	
}
