package de.merten.umgr.web.ui;

import de.merten.umgr.web.eventbus.ApplicationEvent;

@SuppressWarnings("serial")
public class ViewOpenedEvent extends ApplicationEvent {

	
	public ViewOpenedEvent(View view) {
		super(view);
	}

	@Override
	public String getDescription() {
		return "ViewOpenedEvent from " + source;
	}

	public View getView() {
		return (View) source;
	}

}
