package com.tobias.sup.ejb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/*
 * create table USERS (id integer, userName varchar(50), city varchar(50));
 */
@Entity
@TableGenerator(name="userGen", table="GEN_ID", pkColumnName="GEN_KEY", valueColumnName="GEN_VALUE", pkColumnValue="user", initialValue=0, allocationSize=1)
@Table(name = "USERS")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="userGen")
	private int id;
	
	@Column(length=50, name = "userName")
	private String userName;
	
	@Column(length=50, name = "city")
	private String City;
	
	@PrePersist
	public void prepersist() {
		System.out.println("pre persist!!");
	}

	@PreUpdate
	public void preupdate() {
		System.out.println("pre update!!");
	}

	@PostUpdate
	public void postupdate() {
		System.out.println("post update!!");
	}

	@PostLoad
	public void postload() {
		System.out.println("post load!!");
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}
}
