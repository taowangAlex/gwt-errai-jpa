package com.cnpc.video.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cnpc.video.client.shared.Person;

@Stateless(name = "personDAO")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersonDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Person> getPersons(){
		return em.createNamedQuery("allPerson").getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addPerson(Person person){
		em.persist(person);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deletePerson(Person person){
		
		em.remove(em.find(Person.class, person.getId()));
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updatePerson(Person person) {
		em.merge(person);
	}
}
