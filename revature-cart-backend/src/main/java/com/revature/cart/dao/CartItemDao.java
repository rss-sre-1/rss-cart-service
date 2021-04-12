package com.revature.cart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.cart.model.CartItem;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Integer> {
	public List<CartItem> findByCartCartId(int cartId);
	public Optional<CartItem> findByCartCartIdAndProductId(int cartId, int ProductId);
	
	// change provided crud operations to optional for exception handling
	
//	public Optional<CartItem> findById(int id);
//	public Optional<CartItem> deleteById(int id);
}
