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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "demos")
public class Demo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; // SERIAL not null primary key ,

	@Column(name = "user_id")
	private Integer userId; // int not null ,

	@NotNull
	@Column(name = "store_id")
	private Integer storeId; // int not null,

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_entered")
	private Date dateEntered = new Date(); // TIMESTAMP NOT NULL,

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_demo")
	private Date dateOfDemo = new Date(); // TIMESTAMP NOT NULL,

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startTime = new Date(); // TIMESTAMP NOT NULL,

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime = new Date(); // TIMESTAMP NOT NULL,

	private Boolean completed; // boolean NOT NULL default 'false',

	@Size(min = 2)
	@Column(name = "pre_notes")
	private String preNotes; // VARCHAR(4000) NOT NULL ,

	@Column(name = "post_notes")
	private String postNotes; // VARCHAR(4000) NOT NULL ,

	@Min(0)
	@Column(name = "number_of_demos")
	private Integer numberOfDemos; // int not null,

	@Min(0)
	@Column(name = "number_of_people")
	private Integer numberOfPeople; // int not null,

	@Column(name = "store_response")
	private String storeResponse; // VARCHAR(250) NOT NULL

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public Date getDateOfDemo() {
		return dateOfDemo;
	}

	public void setDateOfDemo(Date dateOfDemo) {
		this.dateOfDemo = dateOfDemo;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getPreNotes() {
		return preNotes;
	}

	public void setPreNotes(String preNotes) {
		this.preNotes = preNotes;
	}

	public String getPostNotes() {
		return postNotes;
	}

	public void setPostNotes(String postNotes) {
		this.postNotes = postNotes;
	}

	public Integer getNumberOfDemos() {
		return numberOfDemos;
	}

	public void setNumberOfDemos(Integer numberOfDemos) {
		this.numberOfDemos = numberOfDemos;
	}

	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getStoreResponse() {
		return storeResponse;
	}

	public void setStoreResponse(String storeResponse) {
		this.storeResponse = storeResponse;
	}
}
