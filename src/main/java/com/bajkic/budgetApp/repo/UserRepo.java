package com.bajkic.budgetApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bajkic.budgetApp.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
}
