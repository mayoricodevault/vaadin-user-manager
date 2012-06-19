package de.merten.umgr.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.merten.umgr.backend.bo.Role;
import de.merten.umgr.backend.bo.SpringUser;
import de.merten.umgr.backend.bo.User;
import de.merten.umgr.backend.dao.UserDao;

/**
 * Diese Klasse wird im Spring ApplicationContxt dem AuthenticationProvider
 * zugeordnet. Anhand des Benutzernames wird der Benutzer aus der Datenbank geladen. <p>
 * 
 * Die weitere Authentifizierung und Autorisierung wird dann von Spring vorgenommen.
 */
@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	/**
	 * Sucht den Benutzer mit dem gegebenen Namen aus der Datenbank, ermittelt
	 * die zugehörigen Rollen und liefert eine Implementierung von {@code UserDetails},
	 * die zusätzlich den umgr-User referenziert
	 */
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User umgrUser = userDao.findUserByLogin(username);
		List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
		for (Role role : umgrUser.getRoles()) {
		    roleList.add(new SimpleGrantedAuthority(role.name()));
		}
		roleList.add(new SimpleGrantedAuthority("USER"));
	    SpringUser springUser = new SpringUser(umgrUser, roleList);
		return springUser;
	}
	
	// injected von Spring
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Transactional
	public void saveAll(List<User> userList) {
	    userDao.saveAll(userList);
	}
	
	public List<User> findAll() {
	    return userDao.findAll();
	}

}
