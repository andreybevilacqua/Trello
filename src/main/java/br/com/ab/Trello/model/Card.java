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

	@ManyToOne
	@JoinColumn(name = "listId", nullable = false)
	private ListArea listArea;

	@OneToMany(mappedBy = "card", fetch = FetchType.EAGER)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
	private List<Comment> comments = new ArrayList<Comment>();

	// Constructors
	public Card(){ }
	
	public Card(String title){ this.title = title; }

	public Card(String title, ListArea listArea){
		this.title = title;
		this.listArea = listArea;
	}

	public Integer getId(){ return cardId; }
	
	public String getTitle() { return title; }

	public ListArea getListArea() { return listArea; }

	public List<Comment> getComments() { return comments; }

	// Sets
	public void setId(Integer cardId){ this.cardId = cardId; }

	public void setTitle(String title) { this.title = title; }

	public void setListArea(ListArea listArea) { this.listArea = listArea; }

	public void setComments(ArrayList<Comment> comments) { this.comments = comments; }

}
