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
 * create table PRODUCTS (id integer, Name varchar(50), price float);
 */
@Entity
@TableGenerator(name="productGen", table="GEN_ID", pkColumnName="GEN_KEY", valueColumnName="GEN_VALUE", pkColumnValue="product", initialValue=0, allocationSize=1)
@Table(name = "PRODUCTS")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy = GenerationType.TABLE, generator="productGen")
	@Column(name = "id")
	private int id;
	
	@Column(length=50, name = "name")
	private String Name;
	@Column(name = "price")
	private float price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

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
}
