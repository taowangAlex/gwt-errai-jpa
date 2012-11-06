package com.cnpc.video.client.local;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class LoginForm extends Composite{

	private final TextBox name = new TextBox();
	private final PasswordTextBox password = new PasswordTextBox();
	
	private final Button confirmButton = new Button("登录");
	private final Button cancleButton = new Button("取消");
	
	private ButtonClickHandler confirmHandler;
	private ButtonClickHandler cancleHandler;
	
	
	public LoginForm(){
		
		cancleButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(cancleHandler != null){
					cancleHandler.handle();
				}
			}

		});
		
		
		confirmButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(confirmHandler != null)
				{
					confirmHandler.handle();
				}
			}
		});
		
		Grid g = new Grid(3,2);
		
		g.setBorderWidth(1);
		int row = 0;
		
		g.setText(row, 0, "用户名：");
		g.setWidget(row, 1, name);
		
		row++;
		g.setText(row, 0, "密码：");
		g.setWidget(row, 1, password);
		
		row++;
		g.setWidget(row, 0, confirmButton);
		g.setWidget(row, 1, cancleButton);
		
		initWidget(g);
	}
	
	public void setConfirmHandler(ButtonClickHandler handler){
		this.confirmHandler = handler;
	}
	
	public void setCancleHandler(ButtonClickHandler handler){
		this.cancleHandler = handler;
	}
}
