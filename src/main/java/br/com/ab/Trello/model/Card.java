package br.com.ab.Trello.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cardId;
	private String title;
	private String detail;
	
	public Card(){
		
	}
	
	public Card(String title){
		this.title = title;
	}
	
	public Card(String tile, String detail, Comment comment){
		
		this.title = tile;
		this.detail = detail;
	}

	public void setId(Integer cardId){
		this.cardId = cardId;
	}
	
	public Integer getId(){
		return cardId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
