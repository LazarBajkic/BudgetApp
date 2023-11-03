package com.bajkic.budgetApp.controller;

import java.text.DecimalFormat;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bajkic.budgetApp.model.Expense;
import com.bajkic.budgetApp.model.Income;
import com.bajkic.budgetApp.repo.ExpenseRepo;
import com.bajkic.budgetApp.repo.IncomeRepo;
import com.bajkic.budgetApp.repo.UserRepo;

@Controller
public class UserController {
	
	@Autowired
	UserRepo uRep;
	
	@Autowired
	ExpenseRepo eRep;
	
	@Autowired
	IncomeRepo iRep;
	
	ModelAndView mav;
	
	@GetMapping("/Expenses")
	public ModelAndView expensesPage() {
		mav = new ModelAndView("Expenses");
		List<Expense> expenses=eRep.findAll();
		mav.addObject("expenses", expenses);
		return mav;
	}
	
	@GetMapping("/Income")
	public ModelAndView incomePage(){
		mav = new ModelAndView("Income");
		List<Income> incomeList = iRep.findAll();
		mav.addObject("incomes",incomeList);
		return mav;
	}
	
	@GetMapping("/Analytics")
	public ModelAndView analyticsPage() {
		
		mav = new ModelAndView("Analytics");
		
		List<Expense> expenses=eRep.findAll();
		mav.addObject("expenses", expenses);
		
		List<Income> incomeList = iRep.findAll();
		mav.addObject("incomes",incomeList);
		
		Double[] expenseCost = {};
		double totalExpenses=calculateTotalExpenses(expenseCost);
		mav.addObject("totalExpenses", totalExpenses);
		
		Double[] incomeValue = {};
		double totalIncomeValue=calculateTotalIncome(incomeValue);
		mav.addObject("totalIncomeValue", totalIncomeValue);
		
		double earnings = calculateEarnings(totalExpenses, totalIncomeValue);
		mav.addObject("earnings",earnings);
		
		double savingsPercent = savingsPercentage(totalIncomeValue, totalExpenses);
		DecimalFormat df = new DecimalFormat("#.00");
        String formattedValue = df.format(savingsPercent);
        double roundedValue = Double.parseDouble(formattedValue);
		mav.addObject("savingsPercent", roundedValue);
		
		return mav;
	}
	
	@GetMapping("/editExpense")
	public ModelAndView editExpensesPage(@RequestParam Integer id){
		mav = new ModelAndView("EditExpense");
		Expense expenseList = eRep.findById(id).get();
		mav.addObject("specificExpense",expenseList);
		return mav;
	}
	
	@GetMapping("/editIncome")
	public ModelAndView editIncomePage(@RequestParam Integer id){
		mav = new ModelAndView("EditIncome");
		Income incomeList = iRep.findById(id).get();
		mav.addObject("specificIncome",incomeList);
		return mav;
	}
		
	@PostMapping("/insertIncomeEdit")
	public String insertIncomeEdit(@ModelAttribute("specificIncome") @Validated Income income, @RequestParam Integer id) {
		Income newIncome = new Income(income.getId(),income.getSourceName(),income.getSourceValue());
		iRep.save(newIncome);
		return "redirect:/Income";
	}
	
	@PostMapping("/insertExpenseEdit")
	public String insertExpenseEdit(@ModelAttribute("specificExpense")@Validated Expense expense,@RequestParam Integer id) {
		Expense newExpense = new Expense(expense.getId(),expense.getExpenseName(),expense.getExpenseCoverage(),expense.getExpenseCost());
		eRep.save(newExpense);
		return "redirect:/Expenses";
	}
	
	@GetMapping("/addNewIncome")
	public ModelAndView addNewIncome() {
		mav = new ModelAndView("AddNewIncome");
        Income i = new Income();
        mav.addObject("income",i);
		return mav;
	}
	
	@GetMapping("/addNewExpense")
	public ModelAndView addNewExpense() {
		mav = new ModelAndView("AddNewExpense");
		Expense e = new Expense();
		mav.addObject("expense", e);
		return mav;
	}
	
	@PostMapping("/addIncome")
	public String addIncome(@ModelAttribute("income") Income i) {
		Income newIncome = new Income(i.getSourceName(),i.getSourceValue());
		iRep.save(newIncome);
		return "redirect:/Income";
	}
	
	@PostMapping("/addExpense")
	public String addExpense(@ModelAttribute("expense") Expense e){
		Expense newExpense = new Expense(e.getExpenseName(),e.getExpenseCoverage(),e.getExpenseCost());
		eRep.save(newExpense);
		return "redirect:/Expenses";
	}
	
	@GetMapping("/deleteExpense")
	public String deleteExpense(@RequestParam Integer id) {
		eRep.deleteById(id);
		return "redirect:/Expenses";
	}
	
	@GetMapping("/deleteIncome")
	public String deleteIncome(@RequestParam Integer id) {
		iRep.deleteById(id);
		return "redirect:/Income";
	}
	
	@GetMapping("/getDailyExpenses")
	public ModelAndView dailyExpenses() {
		mav=new ModelAndView("Expenses");
		List<Expense> dailyExpenseList = eRep.findExpenseByCoverage("Daily");
		mav.addObject("expenses", dailyExpenseList);
		return mav;
	}
	
	@GetMapping("/getWeeklyExpenses")
	public ModelAndView weeklyExpense() {
		mav = new ModelAndView("Expenses");
		List<Expense> weeklyExpenseList = eRep.findExpenseByCoverage("Weekly");
		mav.addObject("expenses", weeklyExpenseList);
		return mav;
	}
	
	@GetMapping("/getMonthlyExpenses")
	public ModelAndView monthlyExpenses() {
		mav = new ModelAndView("Expenses");
		List<Expense> monthlyExpenseList = eRep.findExpenseByCoverage("Monthly");
		mav.addObject("expenses", monthlyExpenseList);
		return mav;
	}
	
	public Double calculateTotalExpenses(Double[] allExpenses) {
		double totalExpenseCost=0.0;
		allExpenses = eRep.findTotalExpenses();
		for(int i = 0;i<allExpenses.length;i++) {
			totalExpenseCost=totalExpenseCost + allExpenses[i];
		}
		return totalExpenseCost;
	}
	
	public Double calculateTotalIncome(Double[] totalIncome) {
		double totalIncomeValue=0.0;
		Double[] allIncomeValues= iRep.findTotalIncome();
		for(int i = 0;i<allIncomeValues.length;i++) {
			totalIncomeValue=totalIncomeValue + allIncomeValues[i];
		}
		return totalIncomeValue;
	}
	
	public Double calculateEarnings(Double expenses,Double incomes) {
		double earnings=0;
		earnings=incomes-expenses;
		return earnings;
	}
	
	public Double savingsPercentage(double totalIncome,double totalExpenses) {
		double savingsPercent = ((totalIncome-totalExpenses)/totalIncome)*100;
		return savingsPercent;
	}
	
}
