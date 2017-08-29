package br.com.ab.Trello.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.model.Comment;
import br.com.ab.Trello.service.CommentService;

@WebService
@Stateless
public class CommentController {

	@Inject
	CommentService commentService;

	public void addComment(Comment comment) {
		if (!comment.equals(null)) {
			this.commentService.addComment(comment);
		}
	}

	@WebResult(name = "commentFound")
	public Comment findCommentById(
			@WebParam(name = "comment_id") @XmlElement(required = true, nillable = false) Integer comment_id) {
		return this.commentService.findCommentById(comment_id);
	}

	@WebResult(name = "allComments")
	public List<Comment> findAllComments() {
		return this.commentService.findAllComments();
	}

	@WebResult(name = "allComments")
	public List<Comment> findAllCommentsFromCard(
			@WebParam(name = "card_id") @XmlElement(required = true, nillable = false) Integer card_id) {
		return this.commentService.findAllCommentsFromCard(card_id);
	}

	public void deleteComment(
			@WebParam(name = "comment") @XmlElement(required = true, nillable = false) Comment comment) {
		if (!comment.equals(null)) {
			this.commentService.deleteComment(comment);
		}
	}
}
