package com.tobias.sup.ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

@Stateless
@Local(OrderInterface.class)
@WebService(serviceName = "Order", portName = "OrderPort", endpointInterface = "com.tobias.sup.ejb.OrderInterface", targetNamespace = "http://tobias.com")
public class OrderBean implements OrderInterface {

	@PersistenceContext(unitName = "SUPUnit", type = PersistenceContextType.TRANSACTION)
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersForUser(String userId) {
		Query query = manager.createQuery("SELECT o from Order as o where o.userId = " + userId);        
		return (List<Order>)query.getResultList();
	}

	@Override
	public int createOrder(int productId, int userId) {
		Order order = new Order();
		order.setProductId(productId);
		order.setUserId(userId);
		manager.persist(order);
		return order.getOrderid();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersForProduct(String productId) {
		Query query = manager.createQuery("SELECT o from Order as o where o.productId = " + productId);        
		return (List<Order>)query.getResultList();
	}

	@Override
	public boolean deleteOrder(int id) {
		try {
			Order order = manager.find(Order.class, id);
			manager.remove(order);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrders() {
		Query query = manager.createQuery("SELECT o from Order as o");        
		return (List<Order>)query.getResultList();
	}

	@Override
	public Order getOrder(int orderId) {
		Order order = manager.find(Order.class, orderId);
		if (order == null)
			throw new EJBException("Order not found..!! " + orderId);
		return order;
	}

}