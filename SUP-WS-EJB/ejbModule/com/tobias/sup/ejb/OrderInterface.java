package com.tobias.sup.ejb;

import java.util.List;

import javax.jws.WebService;

@WebService(name = "OrderPortType", targetNamespace = "http://tobias.com")
public interface OrderInterface {

	public List<Order> getOrdersForUser(String userId);
	
	public List<Order> getOrdersForProduct(String productId);
	
	public int createOrder(int productId, int userId);
	
	public boolean deleteOrder(int id);
	
	public List<Order> getOrders();
	
	public Order getOrder(int orderId);
}
