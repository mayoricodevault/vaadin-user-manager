package de.merten.umgr.web;

import de.merten.umgr.web.eventbus.ApplicationEvent;

@SuppressWarnings("serial")
public class LogoutEvent extends ApplicationEvent {

	public LogoutEvent(Object source) {
		super(source);
	}

	@Override
	public String getDescription() {
		return "LogoutEvent";
	}

}
