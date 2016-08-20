package com.bionic.edu.proc.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.bionic.edu.proc.dao.CategoryDao;
import com.bionic.edu.proc.dao.LinkDao;
import com.bionic.edu.proc.entities.Link;
import com.bionic.edu.proc.formmodels.LinkFormModel;

@ControllerAdvice
@Named
public class LinkServiceImpl implements LinkService {

	@Inject
	private LinkDao linkDao;
	@Inject
	private CategoryDao categoryDao;

	public Link findById(int id) {
		return linkDao.findById(id);
	}

	public List<Link> search(LinkFormModel linkForModel) {
		return linkDao.findByProperties(linkForModel);
	}

	public List<Link> getSortedByRating() {
		return linkDao.getSortedByRating();
	}

	@Transactional
	public void add(LinkFormModel linkFormModel) {

		// check if this link is really new
		if (isUnique(linkFormModel.getLinkAddress())) {

			Link link = new Link(linkFormModel.getLinkAddress(), linkFormModel.getTitle(),
					linkFormModel.getDescription(), linkFormModel.getRating());
			link.setCategories(categoryDao.findById(linkFormModel.getCategories()));
			linkDao.save(link);

		} else {
			throw new IllegalArgumentException("A link with this address already exists");
		}
	}

	private boolean isUnique(String linkAddress) {
		return linkDao.findByLinkAddress(linkAddress).isEmpty();
	}

	public List<Link> findAll() {
		return linkDao.findAll();
	}

	@Transactional
	public void delete(int id) {
		linkDao.remove(id);
	}

	@Override
	@Transactional
	public void update(LinkFormModel linkFormModel) {

		// search for a link with the same id as given in the request
		int id = linkFormModel.getId();
		Link link = linkDao.findById(id);

		String oldLinkAddress = link.getLinkAddress();
		String newLinkAddress = linkFormModel.getLinkAddress();

		// check if the link address has been changed
		if (!oldLinkAddress.equals(newLinkAddress)) {

			// check if the address is not taken by another link
			if (!isUnique(newLinkAddress)) {
				throw new IllegalArgumentException("A link with this address already exists");
			}

		}

		// otherwise we can update the link
		link.setLinkAddress(linkFormModel.getLinkAddress());
		link.setTitle(linkFormModel.getTitle());
		link.setDescription(linkFormModel.getDescription());
		link.setRating(linkFormModel.getRating());
		link.setCategories(categoryDao.findById(linkFormModel.getCategories()));
		linkDao.save(link);
	}

}