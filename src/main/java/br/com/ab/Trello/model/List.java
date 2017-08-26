package br.com.ab.Trello.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class List {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer list_id;
	private String title;
	@ManyToOne
	private Card card;
	@OneToOne
	private Dashboard dashboard;
	
	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public List(){
		
	}
	
	public List(String title){
		this.title = title;
		card = new Card();
	}
	
	public Integer getId(){
		return list_id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public Card getCard(){
		return card;
	}
	
	public void setCards(Card card){
		this.card = card;
	}
	
	public void setId(Integer list_id){
		this.list_id = list_id;
	}
	
}
