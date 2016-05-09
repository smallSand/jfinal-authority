package com.jayqqaa12.shiro;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

public class WebSessionListener extends SessionListenerAdapter {

	
	
	@Override
	public void onExpiration(Session session) {
		
		super.onExpiration(session);
	}
	
 

 
}
