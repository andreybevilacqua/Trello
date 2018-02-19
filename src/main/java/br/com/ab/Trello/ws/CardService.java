package br.com.ab.Trello.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ab.Trello.dao.CardDao;
import br.com.ab.Trello.model.Card;

@Stateless
public class CardService {

	@Inject
	CardDao cardDao;
	
	public void addCard (Card card){
		this.cardDao.addCard(card);
	}
	
	public Card findCardById (Integer cardId){
		return this.cardDao.findCardById(cardId);
	}
	
	public List<Card> findAllCards(){
		return this.cardDao.findAllCards();
	}
	
	public List<Card> findAllCardsFromList(Integer listId){
		return this.cardDao.findAllCardsFromListId(listId);
	}
	
	public void deleteCard(Card card){
		this.cardDao.deleteCard(card);
	}
}
