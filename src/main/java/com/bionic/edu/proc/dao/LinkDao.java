package com.bionic.edu.proc.dao;

import java.util.List;

import com.bionic.edu.proc.entities.Link;
import com.bionic.edu.proc.formmodels.LinkFormModel;

public interface LinkDao {
	public Link findById(int id);
	public void save(Link entity);
	public void remove(int id);
	public List<Link> findByProperties(LinkFormModel linkForModel);
	public List<Link> findAll();
	public List<Link> findByLinkAddress(String linkAddress);
	public List<Link> getSortedByRating();
}
