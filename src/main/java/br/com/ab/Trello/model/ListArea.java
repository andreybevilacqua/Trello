package br.com.ab.Trello.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ListArea")
public class ListArea {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer listId;

	@Column(nullable = false)
	private String title;

	@ManyToOne
	@JoinColumn(name="dashboardId", nullable = false)
	private Dashboard dashboard;

	@OneToMany(mappedBy = "listArea")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
	private List<Card> cards = new ArrayList<Card>();

	// Constructors
	public ListArea(){
	}
	
	public ListArea(String title, Dashboard dashboard){
		this.dashboard = dashboard;
		this.title = title;
	}

	// Gets
	public Integer getId(){ return listId; }
	
	public String getTitle(){ return title; }

	public Dashboard getDashboard() { return dashboard; }

	public List<Card> getCards() { return cards; }

	// Sets
	public void setTitle(String title){ this.title = title; }

	public void setId(Integer listId){ this.listId = listId; }

	public void setDashboard(Dashboard dashboard) { this.dashboard = dashboard; }

	public void setCards(ArrayList<Card> cards){ this.cards = cards; }
	
}
