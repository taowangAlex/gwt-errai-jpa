package com.cnpc.video.server.service;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.cnpc.video.client.shared.LoginMessage;

@Stateless(name = "loginService")
public class LoginService implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public void login(LoginMessage loginMessage){
		System.out.println("$$$$$$$$$$$$$$$$ " + loginMessage.getMessage());
	}
	
}
