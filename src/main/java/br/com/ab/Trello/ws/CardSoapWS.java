package br.com.ab.Trello.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.dao.CardDao;
import br.com.ab.Trello.model.Card;

@WebService
@Stateless
public class CardSoapWS {

	@Inject
	CardDao cardDao;
	
	@WebResult(name = "cardFound")
	public Card findCardById(
			@WebParam(name = "cardId") @XmlElement(required = true, nillable = false) Integer cardId) {
		return this.cardDao.findCardById(cardId);
	}

	@WebResult(name = "listOfCards")
	public List<Card> findAllCards() {
		return this.cardDao.findAllCards();
	}

	@WebResult(name = "cardFound")
	public List<Card> findAllCardsFromList(
			@WebParam(name = "listId") @XmlElement(required = true, nillable = false) Integer listId) {
		return this.cardDao.findAllCardsFromListId(listId);
	}

	public void deleteCard(Card card) {
		this.cardDao.deleteCard(card);
	}
}
