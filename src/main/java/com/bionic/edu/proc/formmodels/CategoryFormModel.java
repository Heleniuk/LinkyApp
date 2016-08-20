package com.bionic.edu.proc.formmodels;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.bionic.edu.proc.entities.Category;
import static com.bionic.edu.proc.validation.CategoryValidationConstants.*;
import static com.bionic.edu.proc.validation.CategoryValidationConstants.Errors.*;

public class CategoryFormModel {
	
	private Integer id;
	
	@NotEmpty(message = TITLE_NOT_EMPTY_ERROR)
	@Size(max = TITLE_SIZE, message = TITLE_SIZE_ERROR)
	@Pattern(regexp = TITLE_PATTERN, message = TITLE_PATTERN_ERROR)
	private String title;
	
	@Size(max = DESCRIPTION_SIZE, message = DESCRIPTION_SIZE_ERROR)
	private String description;
	private Integer parentId;
	
	public CategoryFormModel() {};
	
	public CategoryFormModel(Category categoryToUpdate) {
		
		this.id = categoryToUpdate.getId();
		this.title = categoryToUpdate.getTitle();
		this.description = categoryToUpdate.getDescription();
		if (categoryToUpdate.getParent() != null) {
			this.parentId = categoryToUpdate.getParent().getId();
		}
		
	}
	
	public Integer getId() {
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getParentId() {
		return parentId;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}
