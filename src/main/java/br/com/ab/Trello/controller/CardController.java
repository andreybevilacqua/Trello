package br.com.ab.Trello.controller;

import br.com.ab.Trello.dao.CardDao;
import br.com.ab.Trello.model.Card;
import br.com.ab.Trello.model.ListArea;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CardController {

	@Inject
	CardDao cardDao;
	
	public CardController() {
	}

	public Card createNewCard(String title, ListArea listArea){
		return new Card(title, listArea);
	}
	
	public void addCard(Card card) {
		this.cardDao.addCard(card);
	}
	
	public Card findById(Integer cardId) {
		return cardDao.findCardById(cardId);
	}
	
	public void deleteCard(Card card) {
		this.cardDao.deleteCard(card);
	}

}
