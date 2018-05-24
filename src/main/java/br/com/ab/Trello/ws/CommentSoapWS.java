package br.com.ab.Trello.ws;

import br.com.ab.Trello.dao.CommentDao;
import br.com.ab.Trello.model.Comment;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

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
	public Comment findById(
			@WebParam(name = "commentId") @XmlElement(required = true, nillable = false) Integer commentId) {
		return this.commentDao.findById(commentId);
	}

	public void deleteComment(
			@WebParam(name = "comment") @XmlElement(required = true, nillable = false) Comment comment) {
		if (!comment.equals(null)) {
			this.commentDao.deleteComment(comment);
		}
	}
}
