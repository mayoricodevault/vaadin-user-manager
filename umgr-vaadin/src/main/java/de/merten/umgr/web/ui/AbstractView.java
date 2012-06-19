package de.merten.umgr.web.ui;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import de.merten.umgr.web.eventbus.EventBus;
import com.vaadin.ui.ComponentContainer;

@SuppressWarnings("serial")
public abstract class AbstractView implements View, Serializable {

	@Autowired
	protected EventBus eventBus;
	
	public abstract ComponentContainer createMainLayout();
	
	@Override
	public void openView() {
		
		ViewOpenedEvent event = new ViewOpenedEvent(this);
		eventBus.fireEvent(event);
	}	

}
