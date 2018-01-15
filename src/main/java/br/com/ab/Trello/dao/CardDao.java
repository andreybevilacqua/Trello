package br.com.ab.Trello.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		
		Card card = this.entityManager.find(Card.class, cardId);
		return card;
	}
	
	@SuppressWarnings("unchecked")
	public List<Card> findAllCards(){
		return this.entityManager.createQuery("SELECT c FROM card c").getResultList();
	}
	
	public List<Card> findAllCardsFromListId(Integer listId){
		
		List<Card> cardsOfList = new ArrayList<Card>();
		
		String query = "SELECT FROM card c, list l WHERE c.list_id = l.list_id AND l.list_id = :pList_Id";
		
		@SuppressWarnings("unchecked")
		TypedQuery<Card> typedQuery = (TypedQuery<Card>) this.entityManager.createQuery(query);
		typedQuery.setParameter("pList_Id", listId);
		
		cardsOfList = (List<Card>)typedQuery.getResultList();
		
		return cardsOfList;
	}
	
	public void deleteCard(Card card){
		this.entityManager.remove(card);
	}
	
}
