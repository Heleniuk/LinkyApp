package com.bionic.edu.proc.formmodels;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.bionic.edu.proc.entities.Category;
import com.bionic.edu.proc.entities.Link;
import static com.bionic.edu.proc.validation.LinkValidationConstants.*;
import static com.bionic.edu.proc.validation.LinkValidationConstants.Errors.*;

public class LinkFormModel {
	
	private Integer id;

	@Size(max = LINK_ADDRESS_SIZE, message = LINK_ADDRESS_SIZE_ERROR)
	@NotEmpty(message = LINK_ADDRESS_NOT_EMPTY_ERROR)
	@Pattern(regexp = LINK_ADDRESS_PATTERN, message = LINK_ADDRESS_PATTERN_ERROR)
	private String linkAddress;
	
	@NotEmpty(message = TITLE_NOT_EMPTY_ERROR)
	@Size(max = TITLE_SIZE, message = TITLE_SIZE_ERROR)
	private String title;
	
	@Size(max = DESCRIPTION_SIZE, message = DESCRIPTION_SIZE_ERROR)
	private String description;
	
	@Max(value = RATING_MAX_VALUE, message = RATING_MAX_VALUE_ERROR)
	@Min(value = RATING_MIN_VALUE, message = RATING_MIN_VALUE_ERROR)
	private int rating;
	
	@Size(max = CATEGORIES_SIZE, message = CATEGORIES_SIZE_ERROR)
	@NotEmpty(message = CATEGORIES_NOT_EMPTY_ERROR)
	private List<Integer> categories;
	
	public LinkFormModel() {};
	
	public LinkFormModel(Link link) {
		
		this.id = link.getId();
		this.linkAddress = link.getLinkAddress();
		this.title = link.getTitle();
		this.description = link.getDescription();
		this.rating = link.getRating();
		List<Integer> categories = new ArrayList<Integer>();
		for(Category c: link.getCategories()) {
			categories.add(c.getId());
		}
		this.setCategories(categories);
	
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLinkAddress() {
		return linkAddress;
	}
	
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public List<Integer> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Integer> categories) {
		this.categories = categories;
	}
	
}
