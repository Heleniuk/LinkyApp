package com.bionic.edu.proc.services;

import java.util.List;

import com.bionic.edu.proc.entities.Link;
import com.bionic.edu.proc.formmodels.LinkFormModel;

public interface LinkService {
	public Link findById(int id);
	public List<Link> search(LinkFormModel linkFormModel);
	public void add(LinkFormModel linkFormModel);
	public List<Link> findAll();
	public void delete(int id);
	public List<Link> getSortedByRating();
	public void update(LinkFormModel linkFormModel);
}
