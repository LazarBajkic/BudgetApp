package com.bajkic.budgetApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bajkic.budgetApp.model.Expense;

public interface ExpenseRepo extends JpaRepository<Expense,Integer>{
	
	@Query(value="Select * from expenses e where e.expense_coverage=:expenseCoverage",nativeQuery = true)
	List<Expense> findExpenseByCoverage(String expenseCoverage);
	
	@Query(value="Select expense_cost from expenses",nativeQuery=true)
	Double[] findTotalExpenses();

}
