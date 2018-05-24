package br.com.ab.Trello.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ab.Trello.dao.CardDao;
import br.com.ab.Trello.model.Card;
import br.com.ab.Trello.model.ListArea;

@Stateless
public class CardController {

	@Inject
	CardDao cardDao;
	
	public CardController() {
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
