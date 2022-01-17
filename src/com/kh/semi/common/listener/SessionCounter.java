package com.kh.semi.common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 
 * Session 생성/폐기 이벤트 핸들러/리스너
 *
 */

@WebListener
public class SessionCounter implements HttpSessionListener {
	private int activeSessions;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("[Session Created] activeSession = " + ++activeSessions);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {	
		if(activeSessions > 0) 
			activeSessions--;
		System.out.println("[Session Destroyed] activeSession = " + activeSessions);
	}

	
}
