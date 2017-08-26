package br.com.ab.Trello.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;

import br.com.ab.Trello.model.Card;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CardDao {
	
	private EntityManager entityManager;
	
	public void addCard (Card card){
		entityManager.persist(card);
	}
	
	public Card findCardById (Integer cardId){
		
		Card card = entityManager.find(Card.class, cardId);
		return card;
	}
	
}
