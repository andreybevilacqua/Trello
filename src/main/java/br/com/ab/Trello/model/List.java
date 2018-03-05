package br.com.ab.Trello.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class List {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int listId;
	private String title;
	
	private int dashboardId;
	
	public List(){
	}
	
	public List(String title, Integer dashboardId){
		this.dashboardId = dashboardId;
		this.title = title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public Integer getId(){
		return listId;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setId(Integer listId){
		this.listId = listId;
	}

	public int getDashboardId() {
		return dashboardId;
	}
	
}
