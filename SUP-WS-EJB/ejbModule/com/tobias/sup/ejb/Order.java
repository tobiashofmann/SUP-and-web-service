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
 * create table ORDERS (id integer, productid integer, userid integer);
 * create table GEN_ID(GEN_KEY varchar(255), GEN_VALUE integer);
 */
@Entity
@TableGenerator(name="orderGen", table="GEN_ID", pkColumnName="GEN_KEY", valueColumnName="GEN_VALUE", pkColumnValue="order", initialValue=0, allocationSize=1)
@Table(name = "ORDERS")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="orderGen")
	private int id;
	
	@Column(name = "productid")
	private int productId;
	@Column(name = "userid")
	private int userId;
	
	
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
	
	public int getOrderid() {
		return id;
	}
	public void setOrderid(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
