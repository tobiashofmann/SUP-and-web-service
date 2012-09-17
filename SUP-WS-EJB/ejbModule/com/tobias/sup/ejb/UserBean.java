package com.tobias.sup.ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;


@Stateless
@Local(UserInterface.class)
@WebService(serviceName = "User", portName = "UserPort", endpointInterface = "com.tobias.sup.ejb.UserInterface", targetNamespace = "http://tobias.com")
public class UserBean implements UserInterface {

	@PersistenceContext(unitName = "SUPUnit", type = PersistenceContextType.TRANSACTION)
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<User> getUsers() {
		Query query = manager.createQuery("SELECT u from User as u");        
		return (List<User>)query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User getUser(int id) {
		User user = manager.find(User.class, id);
		if (user == null)
			throw new EJBException("User not found..!! " + id);
		return user;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void createUser(User user) {
		manager.persist(user);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteUser(int id) {
		User user = manager.find(User.class, id);
		manager.remove(user);
	}
}
