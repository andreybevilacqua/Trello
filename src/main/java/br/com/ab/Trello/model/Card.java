package br.com.ab.Trello.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Card")
public class Card {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cardId;

	@Column(nullable = false)
	private String title;

	private String detail;

	@ManyToOne
	@JoinColumn(name = "listId", nullable = false)
	private ListArea listArea;

	@OneToMany(mappedBy = "card")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
	private List<Comment> comments = new ArrayList<Comment>();

	// Constructors
	public Card(){ }
	
	public Card(String title){ this.title = title; }
	
	public Card(String tile, String detail, Comment comment){
		this.title = tile;
		this.detail = detail;
	}

	// Gets
	public String getDetail() { return detail; }
	
	public Integer getId(){ return cardId; }
	
	public String getTitle() { return title; }

	public ListArea getListArea() { return listArea; }

	public ArrayList<Comment> getComments() { return (ArrayList<Comment>) comments; }

	// Sets
	public void setId(Integer cardId){ this.cardId = cardId; }

	public void setTitle(String title) { this.title = title; }

	public void setDetail(String detail) { this.detail = detail; }

	public void setListArea(ListArea listArea) { this.listArea = listArea; }

	public void setComments(ArrayList<Comment> comments) { this.comments = comments; }

}
