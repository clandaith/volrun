package com.clandaith.volrun.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "demos")
public class Demo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "user_id", insertable = false, updatable = false)
	private Integer userId;

	@Column(name = "store_id", insertable = false, updatable = false)
	private Integer storeId;

	// @OneToOne
	// @JoinColumn(name = "store_id", insertable = false, updatable = false)
	// private Store demoStore;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_added")
	private Date dateAdded = new Date();

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_event")
	private Date dateOfEvent = new Date();

	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "start_time")
	private Date startTime = new Date();

	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "end_time")
	private Date endTime = new Date();

	private Boolean completed = false;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User demoUser;

	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store demoStore;

	@Column(name = "pre_notes")
	private String preNotes = "";

	@Column(name = "post_notes")
	private String postNotes = "";

	@Min(0)
	@Column(name = "number_of_demos")
	private Integer numberOfDemos = 0;

	@Min(0)
	@Column(name = "number_of_people")
	private Integer numberOfPeople = 0;

	@Column(name = "store_response")
	private String storeResponse = "";

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

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = new Date();
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = new Date();
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

	public User getDemoUser() {
		return demoUser;
	}

	public void setDemoUser(User demoUser) {
		this.demoUser = demoUser;
	}

	public Store getDemoStore() {
		return demoStore;
	}

	public void setDemoStore(Store demoStore) {
		this.demoStore = demoStore;
	}

	// public Store getDemoStore() {
	// return demoStore;
	// }
	//
	// public void setDemoStore(Store demoStore) {
	// this.demoStore = demoStore;
	// }
}
