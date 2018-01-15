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
	
	public Comment findCommentById(Integer commentId){
		Comment comment = this.entityManager.find(Comment.class, commentId);
		return comment;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> findAllComments(){
		return this.entityManager.createQuery("SELECT c FROM comment c").getResultList();
	}
	
	public List<Comment> findAllCommentsFromCardId(Integer cardId){
		
		List<Comment> commentsFromCard = new ArrayList<Comment>();
		
		String query = "SELECT c FROM comment c, card WHERE c.card_id = card.card_id AND card.card_id = :pCard_Id";
		
		@SuppressWarnings("unchecked")
		TypedQuery<Comment> typedQuery = (TypedQuery<Comment>) this.entityManager.createQuery(query);
		typedQuery.setParameter("pCard_Id", cardId);
		
		commentsFromCard = (List<Comment>)typedQuery.getResultList();
		
		return commentsFromCard;
	}
	
	public void deleteComment(Comment comment){
		this.entityManager.remove(comment);
	}

}
