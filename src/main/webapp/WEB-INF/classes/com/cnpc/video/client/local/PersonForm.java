package com.cnpc.video.client.local;

import com.cnpc.video.client.shared.Person;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.TextBox;

public class PersonForm extends Composite{

	private final Person person;
	
	private final TextBox name = new TextBox();
	private final TextBox age = new TextBox();
	private final TextBox phoneNum = new TextBox();
	private final TextBox address = new TextBox();
	
	private RowOperationHandler<Person> saveHandler;
	
	private final Button saveButton = new Button("保存");

	public PersonForm(final Person person){
		
		this.person = person;
		
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				updatePersonFromUI();
				if(saveHandler != null){
					saveHandler.handle(person);
				}
			}
		});
		
		updateUIFromPerson();
		
		Grid personGrid = new Grid(5,2);
		
		int row = 0;
		personGrid.setText(row, 0, "姓名: ");
		personGrid.setWidget(row, 1, name);
		
		row++;
		personGrid.setText(row, 0, "年龄： ");
		personGrid.setWidget(row, 1, age);
		
		row++;
		personGrid.setText(row, 0, "地址：");
		personGrid.setWidget(row, 1, address);
		
		row++;
		personGrid.setText(row, 0, "电话：");
		personGrid.setWidget(row, 1, phoneNum);
		
		row++;
		personGrid.setWidget(row, 1, saveButton);
		
		initWidget(personGrid);
	}
	
	protected void updatePersonFromUI(){
		person.setName(name.getText());
		person.setAddress(address.getText());
		person.setAge(age.getText());
		person.setPhoneNum(phoneNum.getText());
	}
	
	private void updateUIFromPerson(){
		if(person.getName() != null){
			name.setText(person.getName());
		}
		if(person.getAddress() != null){
			address.setText(person.getAddress());
		}
		if(person.getAge() != null){
			age.setText(person.getAge());
		}
		if(person.getPhoneNum() != null){
			phoneNum.setText(person.getPhoneNum());
		}
	}
	
	public void setSaveHandler(RowOperationHandler<Person> handler){
		this.saveHandler = handler;
	}
	
	public void grabFocus(){
		name.setFocus(true);
	}
}
