package br.com.ab.Trello.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.dao.CommentDao;
import br.com.ab.Trello.model.Comment;

@WebService
@Stateless
public class CommentSoapWS {

	@Inject
	CommentDao commentDao;
	
	public void addComment(Comment comment) {
		if (!comment.equals(null)) {
			this.commentDao.addComment(comment);
		}
	}

	@WebResult(name = "commentFound")
	public Comment findCommentById(
			@WebParam(name = "commentId") @XmlElement(required = true, nillable = false) Integer commentId) {
		return this.commentDao.findCommentById(commentId);
	}

	@WebResult(name = "allComments")
	public List<Comment> findAllComments() {
		return this.commentDao.findAllComments();
	}

	@WebResult(name = "allComments")
	public List<Comment> findAllCommentsFromCard(
			@WebParam(name = "cardId") @XmlElement(required = true, nillable = false) Integer cardId) {
		return this.commentDao.findAllCommentsFromCardId(cardId);
	}

	public void deleteComment(
			@WebParam(name = "comment") @XmlElement(required = true, nillable = false) Comment comment) {
		if (!comment.equals(null)) {
			this.commentDao.deleteComment(comment);
		}
	}
}
