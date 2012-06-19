/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.merten.umgr.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.vaadin.annotations.Theme;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.terminal.gwt.server.WrappedHttpServletRequest;
import com.vaadin.ui.Root;

import de.merten.umgr.web.eventbus.ApplicationEvent;
import de.merten.umgr.web.eventbus.ApplicationEventListener;
import de.merten.umgr.web.eventbus.EventBus;
import de.merten.umgr.web.ui.PresenterRegistry;
import de.merten.umgr.web.ui.main.MainViewImpl;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Theme("abado_reindeer")
public class UmgrRoot extends Root {
	
	// logs java.util.logging to slf4j
	static {
		SLF4JBridgeHandler.install();
	}	

	private final Logger log = LoggerFactory.getLogger(UmgrRoot.class);
	
    @Override
    protected void init(WrappedRequest request) {
    	
    	log.trace("Trace");
    	log.debug("Debug");
    	log.info("Info");
    	
    	initSessionData();

    	setCaption(AppData.getMessage("app.title"));

    	PresenterRegistry presenterRegistry = AppData.getSpringBean(PresenterRegistry.class);
    	presenterRegistry.registerPresenter();
    	
    	MainViewImpl mainView = AppData.getSpringBean(MainViewImpl.class);
    	
    	setContent(mainView.createMainLayout());
    	
    	EventBus eventBus = AppData.getSpringBean(EventBus.class);

    	String logoutUrl = WrappedHttpServletRequest.cast(request).getHttpServletRequest().getContextPath() + "/j_spring_security_logout";
    	getApplication().setLogoutURL(logoutUrl);
    	
    	eventBus.addListener(new ApplicationEventListener<LogoutEvent>() {

    		@Override
    		public void handle(LogoutEvent event) {
    			getApplication().close();
    		}

    		@Override
    		public Class<? extends ApplicationEvent> getEventType() {
    			return LogoutEvent.class;
    		}
    	});	
    	
    	log.debug(eventBus.toString());
    	
    	mainView.openView();
    }

	private void initSessionData() {
		
		AppData sessionData = new AppData(getApplication());
    	
    	// Register it as a listener in the application context
        getApplication().getContext().addTransactionListener(sessionData);
        
        // Initialize the session-global data
        AppData.initLocale(getLocale(), "i18n.strings");
	}
    
}
