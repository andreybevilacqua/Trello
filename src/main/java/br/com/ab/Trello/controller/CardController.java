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
			@WebParam(name = "card_id") @XmlElement(required = true, nillable = false) Integer card_Id) {
		return this.cardService.findCardById(card_Id);
	}

	@WebResult(name = "listOfCards")
	public List<Card> findAllCards() {
		return this.cardService.findAllCards();
	}

	@WebResult(name = "cardFound")
	public List<Card> findAllCardsFromList(
			@WebParam(name = "list_id") @XmlElement(required = true, nillable = false) Integer list_id) {
		return this.cardService.findAllCardsFromList(list_id);
	}

	public void deleteCard(Card card) {
		this.cardService.deleteCard(card);
	}
}
