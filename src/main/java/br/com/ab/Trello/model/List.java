package br.com.ab.Trello.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class List {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer list_id;
	private String title;

	public List(){
		
	}
	
	public Integer getId(){
		return list_id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setId(Integer list_id){
		this.list_id = list_id;
	}
	
}
