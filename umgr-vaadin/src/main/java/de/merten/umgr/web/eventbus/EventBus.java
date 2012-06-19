package de.merten.umgr.web.eventbus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
@Scope("session")
public class EventBus implements Serializable {

	private Map<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>> map = new HashMap<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>>();
	
	private final Logger log = LoggerFactory.getLogger(EventBus.class);
	
	@PostConstruct
	void init() {
		log.debug("Creating new EventBus.");
	}
	
	public void addListener(ApplicationEventListener<?> listener) {
		
		Set<ApplicationEventListener<?>> listenerSet = map.get(listener.getEventType());
		if (listenerSet == null) {
			listenerSet = new LinkedHashSet<ApplicationEventListener<?>>();
			map.put(listener.getEventType(), listenerSet);
		}

		listenerSet.add(listener);

		log.debug("Added event bus listener: {}", listener);
		
	}
	
	public void removeListener(ApplicationEventListener<?> listener) {
		
		Set<ApplicationEventListener<?>> listenerSet = map.get(listener.getEventType());
		listenerSet.remove(listener);
	}
	
	public void clear() {
		map.clear();
	}
	
	public void fireEvent(ApplicationEvent event) {

		log.debug("Fire event: {}", event);
		
		Class<? extends ApplicationEvent> eventType = event.getClass();
		
		Set<ApplicationEventListener<?>> listenerSet = map.get(eventType);
		
        for (ApplicationEventListener<?> listener : listenerSet) {
        	@SuppressWarnings("unchecked")
			ApplicationEventListener<ApplicationEvent> l = (ApplicationEventListener<ApplicationEvent>) listener;
        	l.handle(event);
        }
    }

	@Override
	public String toString() {
		
		StringBuilder strb = new StringBuilder("Registered Listener on EventBus: \r\n");
		
		for (Entry<Class<? extends ApplicationEvent>, Set<ApplicationEventListener<?>>> entry : map.entrySet()) {
			strb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\r\n");
		}
		
		return strb.toString();
	}	
	
	
}
