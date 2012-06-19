package de.merten.umgr.web.ui.main;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.merten.umgr.backend.bo.User;
import de.merten.umgr.web.ui.AbstractView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
@Component
@Scope("session")
public class UserInfoViewImpl extends AbstractView implements UserInfoView {

	private Label userInfoLabel;

	public Panel createMainLayout() {
		

	    Panel panel = new Panel("User Info from Database");
	    panel.setWidth("300px");
	    
	    userInfoLabel = new Label();
	    userInfoLabel.setContentMode(Label.CONTENT_XHTML);
	    
	    panel.addComponent(userInfoLabel);
	    
	    return panel;
	}
	
	/* (non-Javadoc)
	 * @see de.merten.umgr.web.ui.main.UserInfoView#setUser(de.merten.umgr.backend.user.User)
	 */
	@Override
	public void setUser(User user) {

	    String userInfo = "ID: " + user.getId() + "<br />"; 
	    userInfo += "Given name: " + user.getGivenname() + "<br />";
	    userInfo += "Surname: " + user.getSurname() + "<br />";
	    userInfo += "Gender: " + user.getGender() + "<br />";
	    userInfo += "Email: " + user.getEmail() + "<br />";
		
	    userInfoLabel.setValue(userInfo);
	}
}
