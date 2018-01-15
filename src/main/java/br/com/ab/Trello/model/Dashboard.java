package br.com.ab.Trello.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dashboard {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer dashboardId;
	private String title;
	private int userId;
	
	public Dashboard(){
		
	}
	
	public Dashboard(String title, int userId){
		setTitle(title);
		setUserId(userId);
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
	
}
