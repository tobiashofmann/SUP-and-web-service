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
@Local(ProductInterface.class)
@WebService(serviceName = "Product", portName = "ProductPort", endpointInterface = "com.tobias.sup.ejb.ProductInterface", targetNamespace = "http://tobias.com")
public class ProductBean implements ProductInterface {

	@PersistenceContext(unitName = "SUPUnit", type = PersistenceContextType.TRANSACTION)
	private EntityManager manager;

	@Override
	public int createProduct(String name, float price) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);

		manager.persist(product);
		return product.getId();
	}

	@Override
	public boolean deleteProduct(int productId) {
		try {
			Product product = manager.find(Product.class, productId);
			manager.remove(product);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Product changeProduct(Product product) {
		Product p = new Product();
		try {
			p = manager.find(Product.class, product.getId());
			p.setName(product.getName());
			p.setPrice(product.getPrice());
			manager.persist(p);			
		}
		catch (Exception e) {
			p.setName("error " + e.getMessage());
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		Query query = manager.createQuery("SELECT p from Product as p");        
		return (List<Product>)query.getResultList();
	}

	@Override
	public Product getProduct(int productId) {
		Product product = manager.find(Product.class, productId);
		if (product == null)
			throw new EJBException("Product not found..!! " + productId);
		return product;
	}

}
