package de.merten.umgr.web.ui.main;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.merten.umgr.web.AppData;
import de.merten.umgr.web.eventbus.ApplicationEvent;
import de.merten.umgr.web.eventbus.ApplicationEventListener;
import de.merten.umgr.web.ui.AbstractPresenter;
import de.merten.umgr.web.ui.AbstractPresenter.ViewInterface;
import de.merten.umgr.web.ui.View;

@SuppressWarnings("serial")
@Component
@Scope("session")
@ViewInterface(MainView.class)
public class MainPresenter extends AbstractPresenter<MainView> {

	@Override
	protected void initPresenter() {
		
		eventBus.addListener(new ApplicationEventListener<NavigationEvent>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return NavigationEvent.class;
			}

			@Override
			public void handle(NavigationEvent event) {
				
				View targetView = AppData.getSpringBean(event.getTarget());
				view.updateCenter(targetView);
			}
		});
	}

	@Override
	protected void viewOpened() {
	}

}
