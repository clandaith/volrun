package com.clandaith.volrun.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "stores")
public class Store extends Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "store_name")
	private String storeName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_added")
	private Date dateAdded;

	// @OneToOne(mappedBy = "demoStore")
	// private Demo demo;

	@OneToMany(mappedBy = "demoStore")
	private List<Demo> demos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	// public Demo getDemo() {
	// return demo;
	// }
	//
	// public void setDemo(Demo demo) {
	// this.demo = demo;
	// }
}
