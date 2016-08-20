package com.bionic.edu.proc.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;


@Entity
public class Link {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String title;
    private String linkAddress;
    private String description;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    		fetch = FetchType.LAZY) 
    @JoinTable(name="CATEGORIZE",
    joinColumns=
        @JoinColumn(name="linkId", referencedColumnName="id"),
    inverseJoinColumns=
        @JoinColumn(name="categoryId", referencedColumnName="id")
    )  
	private Collection<Category> categories;
    
    private int rating;
    
    public Link() {}

	public Link(String linkAddress, String title, String description, int rating) {
		this.linkAddress = linkAddress;
		this.title = title;
		this.description = description;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Category> getCategories() {
		return categories;
	}

	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@PreRemove
	private void removeLinksFromCategoryTree() {
	    for (Category c : categories) {
	        c.getLinks().remove(this);
	    }
	}
	
	@PrePersist
	private void addLinkToCategoryTree() {
	    for (Category c : categories) {
	        c.getLinks().add(this);
	    }
	}
	    
}

