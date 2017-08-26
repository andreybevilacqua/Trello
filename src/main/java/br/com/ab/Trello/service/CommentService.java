package br.com.ab.Trello.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ab.Trello.dao.CommentDao;
import br.com.ab.Trello.model.Comment;

@Stateless
public class CommentService {

	@Inject
	CommentDao commentDao;
	
	public void addComment(Comment comment){
		commentDao.addComment(comment);
	}
	
	public Comment findCommentById(Integer comment_id){
		return commentDao.findCommentById(comment_id);
	}
	
	public List<Comment> findAllComments(){
		return commentDao.findAllComments();
	}
	
	public List<Comment> findAllCommentsFromCard(Integer card_id){
		return commentDao.findAllCommentsFromCardId(card_id);
	}
	
	public void deleteComment(Comment comment){
		commentDao.deleteComment(comment);
	}
}
