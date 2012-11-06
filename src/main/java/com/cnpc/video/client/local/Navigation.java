package com.cnpc.video.client.local;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;

public class Navigation extends Composite{

	private final Button workManagement = new Button("<image src='http://code.google.com/intl/zh-CN/webtoolkit/images/gwt-logo.png'/>");
	private final Button conferenceManagement = new Button("<image src='http://code.google.com/intl/zh-CN/webtoolkit/images/gwt-logo.png'/>");
	private final Button equipmentMonitor = new Button("<image src='http://code.google.com/intl/zh-CN/webtoolkit/images/gwt-logo.png'/>");
	private final Button docManagement = new Button("<image src='http://code.google.com/intl/zh-CN/webtoolkit/images/gwt-logo.png'/>");
	private final Button reportManagement = new Button("<image src='http://code.google.com/intl/zh-CN/webtoolkit/images/gwt-logo.png'/>");
	private final Button personManagement = new Button("<image src='http://code.google.com/intl/zh-CN/webtoolkit/images/gwt-logo.png'/>");
	private final Button equipmentManagement = new Button("<image src='http://code.google.com/intl/zh-CN/webtoolkit/images/gwt-logo.png'/>");
	
	private ButtonClickHandler navigationHandler;
	
	public Navigation(){
		
		workManagement.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(navigationHandler != null){
					navigationHandler.handle();
				}
			}
		});
		
		Grid grid = new Grid(1,7);
		grid.setWidget(0, 0, workManagement);
		grid.setWidget(0, 1, conferenceManagement);
		grid.setWidget(0, 2,equipmentMonitor);
		grid.setWidget(0, 3, docManagement);
		grid.setWidget(0, 4, reportManagement);
		grid.setWidget(0, 5, personManagement);
		grid.setWidget(0, 6, equipmentManagement);
	
		initWidget(grid);
	}
	
	public void setNavigationHandler(ButtonClickHandler buttonClickHandler){
		this.navigationHandler = buttonClickHandler;
	}
		
}
