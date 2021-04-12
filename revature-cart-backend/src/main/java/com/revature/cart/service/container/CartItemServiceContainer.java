package com.revature.cart.service.container;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cart.dao.CartItemDao;
import com.revature.cart.model.CartItem;
import com.revature.cart.service.CartItemService;
import com.revature.exceptions.ItemNotFoundException;

@Service
public class CartItemServiceContainer implements CartItemService {
	private CartItemDao cid;
	
	@Autowired
	public CartItemServiceContainer(CartItemDao cid) {
		this.cid = cid;
	}

	@Override
	public CartItem getCartItemById(int id) {
		if (cid.findById(id) == null) {
			throw new ItemNotFoundException();
		}
		else {
			return cid.findById(id).get();
		}
	}

	@Override
	public CartItem createCartItem(CartItem item) {
		return cid.save(item);
	}

	@Override
	public void deleteCartItemById(int id) {
		if (cid.findById(id) == null) {
			throw new ItemNotFoundException();
		}
		else {
			cid.deleteById(id);
		}
		
	}

	@Override
	public CartItem updateCartItem(CartItem item) {
		return cid.save(item);
	}
	
	@Override
	public List<CartItem> getCartItemsByCartId(int cartId) {
		return cid.findByCartCartId(cartId);
	}
	
	@Override
	public List<CartItem> getAllCartItems() {
		return cid.findAll();
	}

	@Override
	public CartItem getCartItemsByCartIdAndProductId(int cartId, int productId) {
		try {
			return cid.findByCartCartIdAndProductId(cartId, productId).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
}
