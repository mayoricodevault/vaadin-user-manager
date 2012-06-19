package de.merten.umgr.web.ui.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.merten.umgr.web.ui.AbstractView;
import de.merten.umgr.web.ui.View;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
@Component
@Scope("session")
public class MainViewImpl extends AbstractView implements MainView {

	private final Logger log = LoggerFactory.getLogger(MainViewImpl.class);

	private CustomLayout layout;
	
	@Autowired
	private HeaderViewImpl headerView;
	
	public MainViewImpl() {
		log.debug("new MainViewImpl()");
	}
	
	public CustomLayout createMainLayout() {

		log.debug("create main layout");
		
		layout = new CustomLayout("main");
		layout.setSizeFull();
		
        Accordion accordion = new Accordion();
        accordion.addTab(new Label("Energiebereitstellung"), "Energiebereitstellung");
        accordion.addTab(new Label("Energieverteilung und -nutzung"), "Energieverteilung und -nutzung");
        accordion.addTab(new Label("Abwärme"), "Abwärme");
        
        accordion.setSizeFull();
        
        Panel welcomePanel = new Panel();
        welcomePanel.addComponent(new Label("Welcome to the new Energymanager Light!"));
        
        layout.addComponent(headerView.createMainLayout(), "header");
        layout.addComponent(accordion, "left");
        layout.addComponent(new Label("Right"), "right");
        layout.addComponent(new Label("Footer"), "footer");
        layout.addComponent(welcomePanel, "center");
        
        
        return layout;
	}


	public void updateCenter(View view) {
		
		// TODO: das passt noch nicht so ganz!!!
		AbstractView v = (AbstractView) view;
		layout.addComponent(v.createMainLayout(), "center");
		view.openView();
	}
	
	@Override
	public void openView() {
		super.openView();
		headerView.openView();
	}

}
