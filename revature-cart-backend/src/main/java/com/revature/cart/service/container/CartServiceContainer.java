package com.revature.cart.service.container;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cart.dao.CartDao;
import com.revature.cart.model.Cart;
import com.revature.cart.service.CartService;
import com.revature.exceptions.CartNotFoundException;
import com.revature.exceptions.ItemNotFoundException;

@Service
public class CartServiceContainer implements CartService {
	private CartDao cdao;
	
	@Autowired
	public CartServiceContainer(CartDao cdao) {
		super();
		this.cdao = cdao;
	}
	
	@Override
	public Cart createCart(Cart cart) {
		
		try {
			return cdao.save(cart);
		}catch(Exception e) {
			throw new CartNotFoundException();
		}	
	}
	
	@Override
	public List<Cart> getCartsByUserId(int id) {
		return cdao.findByUserId(id);
	}

	@Override
	public Cart getCartById(int id) {
		
		try {
			return cdao.findById(id).get();
		}catch (Exception e) {
			throw new CartNotFoundException();
		}
	}
	
	@Override
	public Cart updateCart(Cart cart) {
		return cdao.save(cart);
	}

	@Override
	public void deleteCartById(int id) {
		try {
			cdao.deleteById(id);
		}catch (Exception e) {
			throw new CartNotFoundException();
		}
	}
}
