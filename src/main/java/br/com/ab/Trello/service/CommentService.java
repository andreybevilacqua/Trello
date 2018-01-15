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
		this.commentDao.addComment(comment);
	}
	
	public Comment findCommentById(Integer commentId){
		return this.commentDao.findCommentById(commentId);
	}
	
	public List<Comment> findAllComments(){
		return this.commentDao.findAllComments();
	}
	
	public List<Comment> findAllCommentsFromCard(Integer cardId){
		return this.commentDao.findAllCommentsFromCardId(cardId);
	}
	
	public void deleteComment(Comment comment){
		this.commentDao.deleteComment(comment);
	}
}
