package com.bionic.edu.proc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.proc.entities.Link;
import com.bionic.edu.proc.formmodels.LinkFormModel;

@Repository
public class LinkDaoImpl implements LinkDao {

	private static final String FIND_ALL = "SELECT l FROM Link l";
	private static final String FIND_BY_LINKADDRESS = "SELECT distinct l FROM Link l where l.linkAddress = :linkAddress";
	private static final String GET_SORTED_BY_RATING = "Select l from Link l order by l.rating desc";

	@PersistenceContext
	EntityManager entityManager;

	public Link findById(int id) {
		return entityManager.find(Link.class, id);
	}

	public List<Link> findAll() {
		TypedQuery<Link> query = entityManager.createQuery(FIND_ALL, Link.class);
		return query.getResultList();
	}

	public void save(Link link) {
		if (link.getId() == 0) {
			entityManager.persist(link);
		} else {
			entityManager.merge(link);
		}
		;
	}

	public void remove(int id) {
		Link link = entityManager.find(Link.class, id);
		if (link != null) {
			this.entityManager.remove(link);
		}
	}

	public List<Link> findByProperties(LinkFormModel linkFormModel) {

		// retrieve the search parameters and put them into maps
		Map<String, List<Integer>> multipleValueProperties = new HashMap<>();
		Map<String, String> singleValueProperties = new HashMap<>();

		multipleValueProperties.put("categories", linkFormModel.getCategories());
		singleValueProperties.put("linkAddress", linkFormModel.getLinkAddress());
		singleValueProperties.put("title", linkFormModel.getTitle());
		singleValueProperties.put("description", linkFormModel.getDescription());

		String queryString = buildQueryByProperties(multipleValueProperties, singleValueProperties);

		TypedQuery<Link> query = entityManager.createQuery(queryString, Link.class);

		query = setSearchParameters(multipleValueProperties, singleValueProperties, query);

		return query.getResultList();

	}

	private String buildQueryByProperties(Map<String, List<Integer>> multipleValueProperties,
			Map<String, String> singleValueProperties) {

		StringBuilder queryString = new StringBuilder(FIND_ALL);
		// a flag to check if the "where" clause is present in the query
		boolean isWherePresent = false;

		for (String m : multipleValueProperties.keySet()) {
			if (multipleValueProperties.get(m) != null) {
				queryString.append(" join l." + m + " " + m);
				queryString.append(" where " + m + ".id in :" + m);
				// if at least one multiple value property is not null, the
				// "where" clause is present in the query
				isWherePresent = true;
			}
		}

		if (isWherePresent) {
			// there is already a where clause, we just need to conjunct another
			// set of conditions
			queryString.append(" and");
		} else {
			// as there were no multiple value properties, the where clause is
			// missing
			queryString.append(" where");
		}

		// conjunct the rest of the conditions
		for (String s : singleValueProperties.keySet()) {
			queryString.append(" l." + s + " like :" + s + " and ");
		}

		// remove the last redundant " and " from the query
		queryString.delete(queryString.length() - 5, queryString.length());

		return queryString.toString();

	}

	private TypedQuery<Link> setSearchParameters(Map<String, List<Integer>> multipleValueProperties,
			Map<String, String> singleValueProperties, TypedQuery<Link> query) {

		// setting multiple value parameters
		for (String m : multipleValueProperties.keySet()) {
			List<Integer> currentValue = multipleValueProperties.get(m);
			if (currentValue != null) {
				query.setParameter(m, currentValue);
			}
		}

		// setting single value parameters
		for (String s : singleValueProperties.keySet()) {
			query.setParameter(s, "%" + singleValueProperties.get(s) + "%");
		}

		return query;
	}

	public List<Link> findByLinkAddress(String linkAddress) {
		TypedQuery<Link> query = entityManager.createQuery(FIND_BY_LINKADDRESS, Link.class);
		query.setParameter("linkAddress", linkAddress);
		return query.getResultList();
	}

	public List<Link> getSortedByRating() {
		TypedQuery<Link> query = entityManager.createQuery(GET_SORTED_BY_RATING, Link.class);
		return query.getResultList();
	}
	
}
