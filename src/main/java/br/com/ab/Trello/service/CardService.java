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
		cardDao.addCard(card);
	}
	
	public Card findCardById (Integer cardId){
		
		return cardDao.findCardById(cardId);
	}
	
	public List<Card> findAllCards(){
		return cardDao.findAllCards();
	}
	
	public List<Card> findAllCardsFromList(Integer list_id){
		
		return cardDao.findAllCardsFromListId(list_id);
	}
	
	public void deleteCard(Card card){
		cardDao.deleteCard(card);
	}
}
