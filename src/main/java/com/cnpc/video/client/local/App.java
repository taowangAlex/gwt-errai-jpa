package com.cnpc.video.client.local;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.errai.ioc.client.api.EntryPoint;

import com.google.gwt.core.client.impl.AsyncFragmentLoader.Logger;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

import com.cnpc.video.client.qualifier.Add;
import com.cnpc.video.client.qualifier.Delete;
import com.cnpc.video.client.qualifier.Update;
import com.cnpc.video.client.shared.GetPersonListMessage;
import com.cnpc.video.client.shared.LoginMessage;
import com.cnpc.video.client.shared.Person;
import com.cnpc.video.client.shared.PersonList;
import com.cnpc.video.client.shared.Response;

/**
 * Main application entry point.
 */
@EntryPoint
public class App {

	@Inject
	private Event<LoginMessage> loginEvent;
	
	@Inject
	private Event<GetPersonListMessage> getPersonListEvent;
	
	@Inject @Add
	private Event<Person> addPersonEvent;
	
	@Inject @Delete
	private Event<Person> deletePersonEvent;
	
	@Inject @Update
	private Event<Person> updatePersonEvent;

	private PersonTable personWidget = new PersonTable();

	private Button newPersonButton = new Button("新建");

	@PostConstruct
	public void buildUI() {

		initPopupLoginPanel();

	}

	private void initPopupLoginPanel() {
		final PopupPanel popupLoginPanel = new PopupPanel(true, true);
		LoginForm loginForm = new LoginForm();

		loginForm.setCancleHandler(new ButtonClickHandler() {

			@Override
			public void handle() {
				popupLoginPanel.hide();
			}
		});

		loginForm.setConfirmHandler(new ButtonClickHandler() {
			final Navigation navigation = new Navigation();

			@Override
			public void handle() {
				loginEvent.fire(new LoginMessage("Login success!"));
				popupLoginPanel.hide();
				navigation.setNavigationHandler(new ButtonClickHandler() {

					@Override
					public void handle() {
						RootPanel.get("navigation").remove(navigation);
						initPersonUI();
					}
				});
				RootPanel.get("navigation").add(navigation);
			}
		});

		popupLoginPanel.setWidget(loginForm);
		popupLoginPanel.setGlassEnabled(false);
		popupLoginPanel.setPopupPosition(600, 400);
		popupLoginPanel.show();
	}

	private void initPersonUI() {
		
		refreshUI();
		
		personWidget.setDeleteHandler(new RowOperationHandler<Person>() {

			@Override
			public void handle(Person person) {
				deletePersonEvent.fire(person);
				refreshUI();
			}
		});

		personWidget.setEditHandler(new RowOperationHandler<Person>() {

			@Override
			public void handle(Person person) {
				PersonForm personForm = new PersonForm(person);
				final PopupPanel personPopupPanel = new PopupPanel(true, true);
				personForm.setSaveHandler(new RowOperationHandler<Person>() {

					@Override
					public void handle(Person person) {
						updatePersonEvent.fire(person);
						refreshUI();
						personPopupPanel.hide();
					}
				});
				personPopupPanel.setWidget(personForm);
				personPopupPanel.setGlassEnabled(true);
				personPopupPanel.setPopupPosition(600, 400);
				personPopupPanel.show();
				personForm.grabFocus();
			}
		});

		newPersonButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PersonForm personForm = new PersonForm(new Person());
				final PopupPanel newPersonPopupPanel = new PopupPanel(true,
						true);
				personForm.setSaveHandler(new RowOperationHandler<Person>() {

					@Override
					public void handle(Person person) {
						addPersonEvent.fire(person);
						refreshUI();
						newPersonPopupPanel.hide();
					}
				});
				newPersonPopupPanel.setWidget(personForm);
				newPersonPopupPanel.setGlassEnabled(true);
				newPersonPopupPanel.setPopupPosition(600, 400);
				newPersonPopupPanel.show();
				personForm.grabFocus();
			}
		});


		RootPanel.get().add(personWidget);
		RootPanel.get().add(newPersonButton);
	}

	private void refreshUI() {
		Window.alert("成功");
		getPersonListEvent.fire(new GetPersonListMessage("get data from database"));
	}

	public void responseHandle(@Observes Response event) {
		Window.alert("" + event.getMessage());
	}
	
	public void recievePersonsHandle(@Observes PersonList event){
		personWidget.removeAllRows();
		personWidget.addAll(event.getPersonList());
	}

}