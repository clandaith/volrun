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
@Table(name = "tournaments")
public class Tournament {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "store_id")
	private Integer storeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_entered")
	private Date dateEntered;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_tournament")
	private Date dateOfTournament;

	@Temporal(TemporalType.TIME)
	@Column(name = "start_time")
	private Date startTime;

	@Temporal(TemporalType.TIME)
	@Column(name = "end_time")
	private Date endTime;

	private Boolean completed;
	@Column(name = "pre_notes")
	private String preNotes;
	@Column(name = "post_notes")
	private String postNotes;
	@Column(name = "number_of_people")
	private Integer numberOfPeople;
	@Column(name = "store_response")
	private String storeResponse;

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

	public Date getDateOfTournament() {
		return dateOfTournament;
	}

	public void setDateOfTournament(Date dateOfTournament) {
		this.dateOfTournament = dateOfTournament;
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
