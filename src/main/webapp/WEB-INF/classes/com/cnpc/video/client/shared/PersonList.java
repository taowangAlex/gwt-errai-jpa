package com.cnpc.video.client.shared;

import java.util.List;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.enterprise.client.cdi.api.Conversational;

@Portable
@Conversational
public class PersonList {

	private List<Person> personList;
	
	public List<Person> getPersonList(){
		return personList;
	}
	
	public void setPersonList(List<Person> personList){
		this.personList = personList;
	}
}
