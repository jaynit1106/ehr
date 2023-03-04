package com.increff.ehr.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractDao {
	
	@PersistenceContext
	private EntityManager em;

	public  <T> void insert(T pojo){
		em.persist(pojo);
	}

	public <T> List<T> getAll(Class<T> clazz){
		TypedQuery<T> query = getQuery("select p from "+clazz.getName()+" p",clazz);
		return query.getResultList();
	}

	public <T> T getById(int id , Class<T> clazz){
		TypedQuery<T> query = getQuery("select p from "+clazz.getName()+" p where id=:id",clazz);
		query.setParameter("id",id);
		return getSingle(query);
	}


	protected <T> T getSingle(TypedQuery<T> query) {
		return query.getResultList().stream().findFirst().orElse(null);
	}
	
	protected <T> TypedQuery<T> getQuery(String jpql, Class<T> clazz) {
		return em.createQuery(jpql, clazz);
	}
	
	protected EntityManager em() {
		return em;
	}

}
