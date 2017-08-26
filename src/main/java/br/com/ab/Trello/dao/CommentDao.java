package br.com.ab.Trello.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ab.Trello.model.Comment;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CommentDao {
	
	private EntityManager entityManager;
	
	public void addComment(Comment comment){
		entityManager.persist(comment);
	}
	
	public Comment findCommentById(Integer comment_id){
		Comment comment = entityManager.find(Comment.class, comment_id);
		return comment;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> findAllComments(){
		return entityManager.createQuery("SELECT c FROM comment c").getResultList();
	}
	
	public List<Comment> findAllCommentsFromCardId(Integer card_id){
		
		List<Comment> commentsFromCard = new ArrayList<Comment>();
		
		String query = "SELECT c FROM comment c, card WHERE c.card_id = card.card_id AND card.card_id = :pCard_Id";
		
		@SuppressWarnings("unchecked")
		TypedQuery<Comment> typedQuery = (TypedQuery<Comment>) entityManager.createQuery(query);
		typedQuery.setParameter("pCard_Id", card_id);
		
		commentsFromCard = (List<Comment>)typedQuery.getResultList();
		
		return commentsFromCard;
	}
	
	public void deleteComment(Comment comment){
		entityManager.remove(comment);
	}

}
