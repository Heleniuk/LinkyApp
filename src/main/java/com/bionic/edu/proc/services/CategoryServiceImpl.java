package com.bionic.edu.proc.services;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.bionic.edu.proc.dao.CategoryDao;
import com.bionic.edu.proc.entities.Category;
import com.bionic.edu.proc.formmodels.CategoryFormModel;

@ControllerAdvice
@Named
public class CategoryServiceImpl implements CategoryService {

	@Inject
    private CategoryDao categoryDao;
    
    public Category findById(int id) { 
	    return categoryDao.findById(id); 
   }

	@Transactional
	public void add(CategoryFormModel categoryFormModel) {
		
		//the title must be unique
		if (isUnique(categoryFormModel.getTitle())) {
			
			Category category = new Category(
					categoryFormModel.getTitle(),
					categoryFormModel.getDescription()
					);
		//if parent id is null, no need to initialize it
			Integer parentId = categoryFormModel.getParentId();
			if(parentId != null) {
				Category parent = categoryDao.findById(parentId);
				category.setParent(parent);
			}
			
			categoryDao.save(category);
			
		} else {
			throw new IllegalArgumentException("A category with this title already exists");
		}
		
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}
    
	public List<Category> getTreeRoots() {
		List<Category> roots = categoryDao.findRoots();
		initChildren(roots);
		return roots;
	}	
	
	@Transactional
	public void delete(int id) {
		if(isValidToRemove(id)) {
			categoryDao.remove(id);
		} else {
			throw new IllegalArgumentException("This category is a node, it cannot be removed");
		}
		
	}
	
	@Override
	@Transactional
	public void update(CategoryFormModel categoryFormModel) {
		
		//search for a category with the same id as given in the request
		Integer id = categoryFormModel.getId();
		Category category = categoryDao.findById(id);
		Integer parentId = categoryFormModel.getParentId();
		
		//if parent id is not null, we must ensure that it's not tied to itself as a parent
		if (parentId != null) {
			if (parentId == id) {
				throw new IllegalArgumentException("A category cannot be its own parent");
			} 
		//otherwise we can update the parent
			Category parent = categoryDao.findById(parentId);
			category.setParent(parent);
		}
		
		//check if the title has been changed
		String oldTitle = category.getTitle();
		String newTitle = categoryFormModel.getTitle();
		if (!oldTitle.equals(newTitle)) {
			
		//check if the title is not taken by another category
			if (!isUnique(newTitle)) {
				throw new IllegalArgumentException("A category with this title already exists");
			}
			
		}
		//otherwise we can update the title and the description
		category.setTitle(categoryFormModel.getTitle()); 
		category.setDescription(categoryFormModel.getDescription());
		categoryDao.save(category);		
		
	}
	
	private boolean isUnique(String title) {
		return categoryDao.findByTitle(title).isEmpty(); 
	}
	
	private void initChildren(List<Category> categories) {
		//initialize children recursively
		for(Category c: categories) {
			c.setChildren(categoryDao.findChildren(c.getId()));
			if(c.getChildren() != null) {		
				initChildren(c.getChildren());
			}
		}		
	}
	
	private boolean isValidToRemove(int id) {
		boolean hasNoChildCategories = categoryDao.findChildren(id).isEmpty();
		boolean hasNoLinks = categoryDao.findById(id).getLinks().isEmpty();
		return hasNoChildCategories && hasNoLinks;
	}

}
