package br.com.ab.Trello.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.ab.Trello.model.Comment;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CommentDao {
	
	@PersistenceContext(unitName = "Trello")
	private EntityManager entityManager;
	
	public void addComment(Comment comment){
		this.entityManager.persist(comment);
	}
	
	public Comment findById(Integer commentId){ return this.entityManager.find(Comment.class, commentId); }
	
	public void deleteComment(Comment comment){
		this.entityManager.remove(comment);
	}

}
