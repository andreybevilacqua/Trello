package br.com.ab.Trello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Dashboard {

	@XmlTransient
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dashboard_id")
	private Integer dashboardId;
	@XmlElement(required=true)
	private String title;
	@XmlElement(required=true)
	@Column(name="user_id")
	private int userId;
	
	@Transient
	private int numberOfLists;
	
	public Dashboard(){
		
	}
	
	public Dashboard(String title, int userId){
		setTitle(title);
		setUserId(userId);
		numberOfLists = 0;
	}
	
	public Dashboard(int id, String title, int userId){
		setTitle(title);
		setUserId(userId);
	}

	public String getTitle() {
		return title;
	}
	
	public void setId(int id){
		this.dashboardId = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getId(){
		return dashboardId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int user_Id) {
		this.userId = user_Id;
	}

	public int getNumberOfLists() {
		return numberOfLists;
	}
	
	public void addList() {
		numberOfLists = numberOfLists + 1;
	}
	
}
