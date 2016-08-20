package com.bionic.edu.proc.services;

import java.util.List;

import com.bionic.edu.proc.entities.Category;
import com.bionic.edu.proc.formmodels.CategoryFormModel;

public interface CategoryService {
	public Category findById(int id);
	public void add(CategoryFormModel categoryFormModel);
	public List<Category> findAll();
	public List<Category> getTreeRoots();
	public void delete(int id);
	public void update(CategoryFormModel categoryFormModel);
}
