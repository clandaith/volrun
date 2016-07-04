package com.clandaith.volrun.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "files")
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; // SERIAL not null primary key ,
	@Column(name = "file_name")
	private String fileName; // VARCHAR(250) unique NOT NULL ,
	private String description; // VARCHAR(250) NOT NULL ,

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_entered")
	private Date dateEntered; // TIMESTAMP NOT NULL

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}
}
