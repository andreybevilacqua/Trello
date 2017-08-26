package br.com.ab.Trello.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Comment {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer comment_id;
	private Date dateOfTheComment;
	private String comment;
	@OneToOne
	private User user;
	@OneToOne
	private Card card;
	
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Comment(){
		
	}
	
	public Comment(User user, Date dateOfTheComment, String comment){
		this.user = user;
		this.dateOfTheComment = dateOfTheComment; // Corrigir
		this.comment = comment;
	}
	
	public void setId(Integer comment_id){
		this.comment_id = comment_id;
	}
	
	public Integer getId(){
		return comment_id;
	}
	
	public User getUser(){
		return user;
	}
	
	public Date getDateOfTheComment() {
		return dateOfTheComment;
	}
	
	public void setDateOfTheComment(Date dateOfTheComment) {
		this.dateOfTheComment = dateOfTheComment;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
