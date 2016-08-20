package com.bionic.edu.proc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.proc.entities.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	private static final String FIND_BY_IDS = "SELECT c FROM Category c WHERE c.id in :ids";
	private static final String FIND_ALL = "SELECT c FROM Category c";
	private static final String FIND_BY_TITLE = "SELECT distinct l FROM Category l where l.title = :title";
	private static final String FIND_ROOTS = "SELECT c FROM Category c WHERE c.parent is NULL";
	private static final String FIND_CHILDREN = "SELECT c FROM Category c WHERE c.parent.id = :id";
	
	@PersistenceContext
	EntityManager entityManager;
	
	public Category findById(int id) {		
		return entityManager.find(Category.class, id);
	}
	
	public List<Category> findById(List<Integer> ids) {
		TypedQuery<Category> query = entityManager.createQuery(FIND_BY_IDS, Category.class);
		query.setParameter("ids", ids);
		return query.getResultList();
	}
	
	public List<Category> findAll() {
		TypedQuery<Category> query = entityManager.createQuery(FIND_ALL, Category.class);
    	List<Category> categories = query.getResultList();
    	return categories;    
	}

	public void save(Category category) {
		
		if (category.getId() == 0) {
			entityManager.persist(category);
		} else {
			entityManager.merge(category);
		}
					
	}

	public void remove(int id) {
		Category category = entityManager.find(Category.class, id);
	     if (category != null){
	     	this.entityManager.remove(category);
	     }		
	}

	public List<Category> findByTitle(String title) {
		TypedQuery<Category> query = entityManager.createQuery(FIND_BY_TITLE, Category.class);
		query.setParameter("title", title);
		return query.getResultList();
	}
	
	public List<Category> findRoots() {
		TypedQuery<Category> query = entityManager.createQuery(FIND_ROOTS, Category.class);
		return query.getResultList();
	}
	
	public List<Category> findChildren(int id) {
		TypedQuery<Category> query = entityManager.createQuery(FIND_CHILDREN, Category.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
}
