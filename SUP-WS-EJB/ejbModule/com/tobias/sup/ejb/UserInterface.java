package com.tobias.sup.ejb;

import java.util.List;

import javax.jws.WebService;
@WebService(name = "UserPortType", targetNamespace = "http://tobias.com")
public interface UserInterface {
	
	public List<User> getUsers();
	
	public User getUser(int id);
	
	public void createUser(User user);

	public void deleteUser(int id);

}
