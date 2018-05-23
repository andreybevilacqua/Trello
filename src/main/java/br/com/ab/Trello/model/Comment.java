package br.com.ab.Trello.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "Comment")
public class Comment {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer commentId;

	@Column(nullable = false)
	private Date dateOfTheComment;

	private String comment;

	@ManyToOne
	@JoinColumn(name = "cardId", nullable = false)
	private Card card;

	// Constructors
	public Comment(){ }
	
	public Comment(Date dateOfTheComment, String comment){
		this.dateOfTheComment = dateOfTheComment;
		this.comment = comment;
	}

	// Gets
	public Integer getId(){
		return commentId;
	}

	public Date getDateOfTheComment() {
		return dateOfTheComment;
	}

	public String getComment() {
		return comment;
	}

	public Card getCard(){ return card; }

	// Sets
	public void setId(Integer commentId){
		this.commentId = commentId;
	}

	public void setDateOfTheComment(Date dateOfTheComment) {
		this.dateOfTheComment = dateOfTheComment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCard(Card card){ this.card = card; }

}
