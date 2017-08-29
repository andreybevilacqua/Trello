package br.com.ab.Trello.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dashboard {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer dashboard_Id;
	private String title;
	private int user_Id;
	
	public Dashboard(){
		
	}
	
	public Dashboard(String title, int userId){
		setTitle(title);
		setUser_Id(userId);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	
}
