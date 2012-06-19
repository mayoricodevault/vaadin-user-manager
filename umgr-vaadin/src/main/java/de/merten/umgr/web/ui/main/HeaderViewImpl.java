package de.merten.umgr.web.ui.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.merten.umgr.web.LogoutEvent;
import de.merten.umgr.web.ui.AbstractView;
import com.vaadin.event.MouseEvents;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@Component
@Scope("session")
public class HeaderViewImpl extends AbstractView implements HeaderView {

	private final Logger log = LoggerFactory.getLogger(HeaderViewImpl.class);
	
	private HorizontalLayout layout;
	
	private String salutation;
	private Button profileButton;
	
	private Embedded logo;
	
	public HorizontalLayout createMainLayout() {
		
		log.debug("create header layout.");
		
		layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
		layout.setMargin(false, true, false, false);
		
		logo = new Embedded(null, new ThemeResource("img/logo_header.gif"));

        profileButton = new Button();
        profileButton.setStyleName("link");
        profileButton.setSizeUndefined();
        
//        Embedded logoutButton = new Embedded();
//        logoutButton.setMimeType("img/gif");
//        logoutButton.setStyleName("b_logout");
//        logoutButton.setDescription("logout");
//        logoutButton.setWidth("40px");
//        logoutButton.setHeight("40px");
        Button logoutButton = new Button();
        logoutButton.setCaption("Logout");

        Label spacer = new Label("&nbsp;", Label.CONTENT_XHTML);

        layout.addComponent(logo);        
        layout.addComponent(spacer);
        layout.addComponent(profileButton);
        layout.addComponent(logoutButton);
        
        layout.setComponentAlignment(profileButton, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);
        layout.setExpandRatio(spacer, 1.0f);
        
        //logoutButton.addListener(new MouseEvents.ClickListener() {
        logoutButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				eventBus.fireEvent(new LogoutEvent(this));
			}
		});
        
        profileButton.addListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				eventBus.fireEvent(new NavigationEvent(this, UserInfoView.class));
			}
		});        
        
        
        return layout;
	}
	
	@Override
	public String getSalutation() {
		return salutation;
	}

	@Override
	public void setSalutation(String salutation) {
		this.salutation = salutation;
		profileButton.setCaption(salutation);
	}	
}
