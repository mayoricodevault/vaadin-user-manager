package de.merten.umgr.web.ui.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import de.merten.umgr.backend.bo.User;
import de.merten.umgr.backend.dao.UserDao;
import de.merten.umgr.web.ui.AbstractPresenter;
import de.merten.umgr.web.ui.AbstractPresenter.ViewInterface;

@SuppressWarnings("serial")
@Component
@Scope("session")
@ViewInterface(HeaderView.class)
public class HeaderPresenter extends AbstractPresenter<HeaderView> {

	private final Logger log = LoggerFactory.getLogger(HeaderPresenter.class);
	
	@Autowired
	private transient UserDao userService;
	
	protected void initPresenter() {
		log.debug("init HeaderPresenter ...");
	}
	
	public void viewOpened() {
		
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userService.findUserByLogin(login);
		view.setSalutation(user.getGivenname() + " " + user.getSurname());		
	}
}
