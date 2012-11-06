package com.cnpc.video.server.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cnpc.video.client.shared.Person;
import com.cnpc.video.dao.PersonDAO;

@Stateless(name = "personService")
public class PersonService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject 
	PersonDAO personDAO;
	
	public List<Person> getPersons(){
		return personDAO.getPersons();
	}
	
	public void addPerson(Person person){
		personDAO.addPerson(person);
	}
	
	public void deletePerson(Person person){
		personDAO.deletePerson(person);
	}
	
	public void updatePerson(Person person){
		personDAO.updatePerson(person);
	}
}
