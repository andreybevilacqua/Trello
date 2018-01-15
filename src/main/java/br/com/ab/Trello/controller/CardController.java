package br.com.ab.Trello.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.model.Card;
import br.com.ab.Trello.service.CardService;

@WebService
@Stateless
public class CardController {

	@Inject
	private CardService cardService;


	@WebResult(name = "cardFound")
	public Card findCardById(
			@WebParam(name = "cardId") @XmlElement(required = true, nillable = false) Integer cardId) {
		return this.cardService.findCardById(cardId);
	}

	@WebResult(name = "listOfCards")
	public List<Card> findAllCards() {
		return this.cardService.findAllCards();
	}

	@WebResult(name = "cardFound")
	public List<Card> findAllCardsFromList(
			@WebParam(name = "listId") @XmlElement(required = true, nillable = false) Integer listId) {
		return this.cardService.findAllCardsFromList(listId);
	}

	public void deleteCard(Card card) {
		this.cardService.deleteCard(card);
	}
}
