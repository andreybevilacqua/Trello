package br.com.ab.Trello.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Card {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer card_id;
	private String title;
	private String detail;
	@ManyToOne
	private Comment comment;
	@OneToOne
	private List list;
	
	public Card(){
		
	}
	
	public Card(String title){
		this.title = title;
	}
	
	public Card(String tile, String detail, Comment comment){
		
		this.title = tile;
		this.detail = detail;
		this.comment = comment;
	}

	public void setId(Integer card_id){
		this.card_id = card_id;
	}
	
	public Integer getId(){
		return card_id;
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

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
