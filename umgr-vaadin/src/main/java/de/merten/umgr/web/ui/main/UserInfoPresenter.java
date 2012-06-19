package de.merten.umgr.web.ui.main;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import de.merten.umgr.backend.bo.User;
import de.merten.umgr.backend.dao.UserDao;
import de.merten.umgr.web.AppData;
import de.merten.umgr.web.ui.AbstractPresenter;
import de.merten.umgr.web.ui.AbstractPresenter.ViewInterface;

@SuppressWarnings("serial")
@Component
@Scope("session")
@ViewInterface(UserInfoView.class)
public class UserInfoPresenter extends AbstractPresenter<UserInfoView> {

	@Override
	protected void viewOpened() {
		
	    UserDao userService = AppData.getSpringBean(UserDao.class);
	    User user = userService.findUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		
	    view.setUser(user);
	}

	@Override
	protected void initPresenter() {
	}

}
