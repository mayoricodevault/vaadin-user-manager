package de.merten.umgr.backend.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDao<T extends Serializable> {
    
	private Class<T> persistentClass;
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected Session getSession() {
	    return (Session)entityManager.unwrap(Session.class);
	}
	
	protected void setClazz(final Class<T> clazzToSet) {
		this.persistentClass = clazzToSet;
	}

	@Transactional(readOnly = true)
	public T findById(final Long id) {
		return entityManager.find(persistentClass, id);
	}
	
    @Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return entityManager.createQuery("from " + persistentClass.getName()).getResultList();
	}

    @Transactional
	public void persist(final T entity) {
		entityManager.persist(entity);
	}
	
    @Transactional
	public void persistAll(final List<T> entityList) {
	    for (T entity : entityList) {
	        persist(entity);
	    }
    }
	
    @Transactional
	public void saveAll(List<T> entityList) {
	    for (T entity : entityList) {
	        getSession().saveOrUpdate(entity);
	    }
	}

	public T update(final T entity) {
		return entityManager.merge(entity);
	}

	public void delete(final T entity) {
		doDelete(entity);
	}

	public void delete(final Long entityId) {
		final T entity = findById(entityId);
		doDelete(entity);
	}
	
	@Transactional
	private void doDelete(final T entity) {
		entityManager.remove(entity);
//		entityManager.remove(entityManager.merge(entity));
	}
}
