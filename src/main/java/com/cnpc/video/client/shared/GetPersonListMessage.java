package com.cnpc.video.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class GetPersonListMessage {

	private int id;
	private String message;

	public GetPersonListMessage() {

	}

	public GetPersonListMessage(String message){
		this.message = message;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
