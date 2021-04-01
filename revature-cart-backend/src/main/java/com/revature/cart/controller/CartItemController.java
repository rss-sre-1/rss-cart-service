package com.revature.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cart.model.CartItem;
import com.revature.cart.service.container.CartItemServiceContainer;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin
@RestController
public class CartItemController {
	
	private static final Logger log = LoggerFactory.getLogger(CartItemController.class);
	
	private CartItemServiceContainer cisc;
	
	@Autowired
	public CartItemController(CartItemServiceContainer cisc) {
		this.cisc = cisc;
	}
	
	String event = "event";
	
	@PostMapping("/cartitem")
	@ApiOperation(value = "Create CartItem", notes = "Saves the CartItem object to the database. "
			+ "If a CartItem with a matching Cart and Product ID already exists in the database, "
			+ "the existing CartItem will add the passed CartItem's quantity to its own.", response = CartItem.class)
	public CartItem createCartItem(@RequestBody CartItem ci) {
		MDC.put(event, "createCartItem");
		CartItem other = cisc.getCartItemsByCartIdAndProductId(ci.getCart().getCartId(), ci.getProductId());
		if (other != null) {
			other.setQuantity(ci.getQuantity() + other.getQuantity());
		    log.info("update cart item quantity");
			return cisc.updateCartItem(other);
		}
		log.info("create new cart item");
		MDC.clear();
		return cisc.createCartItem(ci);
	}
	
	@GetMapping("/cartitem/{id}")
	@ApiOperation(value = "Get CartItem by CartItem ID", notes = "Gets the CartItem object based on the CartItem ID", response = CartItem.class)
	public CartItem getCartItemById(@ApiParam(value = "CartItem ID", required = true) @PathVariable("id") int id) {
		MDC.put(event, "getCartItemById");
		log.info("get cart item by item id {}", id);
		MDC.clear();
		return cisc.getCartItemById(id);
	}
	
	@GetMapping("/cartitems/{id}")
	@ApiOperation(value = "Get all CartItems by Cart ID", notes = "Returns all CartItems that have the matching Cart by Cart ID", response = CartItem.class)
	public List<CartItem> getAllCartItems(@ApiParam(value = "Cart ID", required = true) @PathVariable("id") int cartId) {
		MDC.put(event, "getAllCartItems");
		log.info("get all cart item by cart id {}", cartId);
		MDC.clear();
		return cisc.getCartItemsByCartId(cartId);
	}
	
	@PutMapping("/cartitem")
	@ApiOperation(value = "Update CartItem", notes = "Updates the CartItem object in the database", response = CartItem.class)
	public CartItem updateCartItem(@ApiParam(value = "CartItem", required = true) @RequestBody CartItem ci) {
		MDC.put(event, "updateCartItem");
		log.info("update cart item by id {}", ci.getCartItemId());
		MDC.clear();
		return cisc.updateCartItem(ci);
	}
	
	@DeleteMapping("/cartitem/{id}")
	@ApiOperation(value = "Delete CartItem", notes = "Deletes the CartItem object from the database based on CartItem ID")
	public void deleteCartItemById(@ApiParam(value = "CartItem ID", required = true) @PathVariable("id") int id) {
		MDC.put(event, "deleteCartItemById");
		log.info("delete cart item by id {}", id);
		MDC.clear();
		cisc.deleteCartItemById(id);
	}
	
}
