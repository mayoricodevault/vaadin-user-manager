package de.merten.umgr.web.ui.main;

import de.merten.umgr.backend.bo.User;
import de.merten.umgr.web.ui.View;

public interface UserInfoView extends View {

	void setUser(User user);

}