package br.com.ab.Trello.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ab.Trello.model.Card;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CardDao {
	
	@PersistenceContext(unitName = "Trello")
	private EntityManager entityManager;
	
	public void addCard (Card card){
		this.entityManager.persist(card);
	}
	
	public Card findCardById(Integer cardId){
		return this.entityManager.find(Card.class, cardId);
	}
	
	public void deleteCard(Card card){
		this.entityManager.remove(card);
	}

	public void editCardTitle(String cardTitle, int cardId){
		Card card = this.entityManager.find(Card.class, cardId);
		card.setTitle(cardTitle);
		this.entityManager.merge(card);
	}
	
}
