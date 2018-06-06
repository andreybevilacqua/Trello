package br.com.ab.Trello.ws;

import br.com.ab.Trello.dao.CardDao;
import br.com.ab.Trello.model.Card;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService
@Stateless
public class CardSoapWS {

	@Inject
	CardDao cardDao;
	
	@WebResult(name = "cardFound")
	public Card findById(
			@WebParam(name = "cardId") @XmlElement(required = true, nillable = false) Integer cardId) {
		return this.cardDao.findCardById(cardId);
	}

	public void deleteCard(int cardId) {
		this.cardDao.deleteCard(cardId);
	}
}
