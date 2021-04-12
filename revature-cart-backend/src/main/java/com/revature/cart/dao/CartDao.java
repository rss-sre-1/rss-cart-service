package com.revature.cart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.cart.model.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
	public List<Cart> findByUserId(int userId);
	
	// add optionals for exception handling
	
//	public Optional<Cart> findById(int id);
//	public Optional<Cart> deleteById(int id);
	
}
