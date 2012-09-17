package com.tobias.sup.ejb;

import java.util.List;

import javax.jws.WebService;

@WebService(name = "ProductPortType", targetNamespace = "http://tobias.com")
public interface ProductInterface {

	public int createProduct(String name, float price);
	
	public boolean deleteProduct(int productId);
	
	public Product changeProduct(Product product);
	
	public List<Product> getAllProducts();
	
	public Product getProduct(int productId);
}
