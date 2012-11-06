package com.cnpc.video.server;

import java.util.ArrayList;
import java.util.List;

import com.cnpc.video.client.qualifier.Add;
import com.cnpc.video.client.qualifier.Delete;
import com.cnpc.video.client.qualifier.Update;
import com.cnpc.video.client.shared.GetPersonListMessage;
import com.cnpc.video.client.shared.LoginMessage;
import com.cnpc.video.client.shared.Person;
import com.cnpc.video.client.shared.PersonList;
import com.cnpc.video.client.shared.Response;
import com.cnpc.video.server.service.LoginService;
import com.cnpc.video.server.service.PersonService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.solder.logging.Logger;

/**
 * A very simple CDI based service.
 */
@ApplicationScoped
public class ErraiCDIService {
  @Inject
  private Event<Response> responseEvent;
  
  @Inject 
  private Event<PersonList> personListEvent;
  
  @Inject
  LoginService loginService;
  
  @Inject
  PersonService personService;
  
  Logger logger = Logger.getLogger(ErraiCDIService.class);

  public void handleMessage(@Observes LoginMessage event) {
    loginService.login(event);
    responseEvent.fire(new Response(event.getMessage() + " @ timemillis: " + System.currentTimeMillis()));
  }
  
  public void handleGetPersonListMessage(@Observes GetPersonListMessage event){
	  logger.info("~~~~~~~~~~~~~~" + event.getMessage());
	  List<Person> persons = new ArrayList<Person>();
	  persons = personService.getPersons();
	  
	  PersonList personList = new PersonList();
	  personList.setPersonList(persons);
	  personListEvent.fire(personList);
  }
  
  public void handleAddPerson(@Observes @Add Person person){
	  personService.addPerson(person);
	  logger.info("$$$$$$$$$$$$$$ " + "新建person成功");
  }
  
  public void handleDeletePerson(@Observes @Delete Person person){
	  personService.deletePerson(person);
	  logger.info("$$$$$$$$$$$$$$ " +  "删除person成功");
  }
  
  public void handleUpdatePerson(@Observes @Update Person person){
	  personService.updatePerson(person);
	  logger.info("$$$$$$$$$$$$$$ " +  "更新person成功");
  }
}
