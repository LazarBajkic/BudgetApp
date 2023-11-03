package com.bajkic.budgetApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bajkic.budgetApp.model.Income;

public interface IncomeRepo extends JpaRepository<Income, Integer>{
	
	@Query(value="Select source_value from sources_of_income",nativeQuery=true)
	Double[] findTotalIncome();
}
