package de.merten.umgr.backend.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import de.merten.umgr.backend.bo.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> { //implements UserDao {

	public UserDao() {
		setClazz(User.class);
	}
	
	public User findUserByLogin(String login) {
		
		TypedQuery<User> query = entityManager.createQuery("select u from User u where u.login = :login", User.class);
		query.setParameter("login", login);
		
		return query.getSingleResult();
	}
}
