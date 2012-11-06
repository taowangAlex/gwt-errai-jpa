package com.cnpc.video.client.local;

import java.util.List;

import com.cnpc.video.client.shared.Person;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;

public class PersonTable extends FlexTable{

	private RowOperationHandler<Person> deleteHandler;
	private RowOperationHandler<Person> editHandler;

	public PersonTable(){
		addStyleName("personTable");
		ColumnFormatter columnFormatter = getColumnFormatter();
		columnFormatter.setStyleName(4, "deleteButton");
		columnFormatter.setStyleName(5, "editButton");
	}
	
	public void addAll(List<Person> persons){
		for(Person person : persons){
			add(person);
		}
	}
	public void add(final Person person){
		Button editButton = new Button("编辑...");
		editButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(editHandler != null){
					editHandler.handle(person);
				}
			}
		});
		
		Button deleteButton = new Button("删除...");
		deleteButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(deleteHandler != null){
					deleteHandler.handle(person);
				}
			}
		});
		
		int row = getRowCount();
	    insertRow(row);
	    insertCells(row, 0,4);
	    setText(row, 0, person.getName()== null? "*" : person.getName());
	    setText(row, 1, person.getAddress()== null? "*" : person.getAddress());
	    setText(row, 2, person.getAge() == null? "*" : person.getAge());
	    setText(row, 3, person.getPhoneNum()== null? "*" : person.getPhoneNum());
	    setWidget(row, 4, editButton);
	    setWidget(row, 5, deleteButton);
	}
	
	public void setDeleteHandler(RowOperationHandler<Person> handler) {
		deleteHandler = handler;
	}

	public void setEditHandler(RowOperationHandler<Person> handler) {
		editHandler = handler;
	}
}
