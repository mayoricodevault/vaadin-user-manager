package de.merten.umgr.web.ui.main;

import de.merten.umgr.web.eventbus.ApplicationEvent;
import de.merten.umgr.web.ui.View;

@SuppressWarnings("serial")
public class NavigationEvent extends ApplicationEvent {

	private final Class<? extends View> target;
	
	public NavigationEvent(Object source, Class<? extends View> target) {
		super(source);
		this.target = target;
	}

	@Override
	public String getDescription() {
		return "NavigationEvent to view: " + target;
	}

	public Class<? extends View> getTarget() {
		return target;
	}
}
