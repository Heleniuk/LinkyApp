package com.bionic.edu.proc.dao;

import java.util.List;

import com.bionic.edu.proc.entities.Category;

public interface CategoryDao {
	
	public Category findById(int id);

	public List<Category> findById(List<Integer> ids);

	public void save(Category category);

	public void remove(int id);

	public List<Category> findAll();

	public List<Category> findRoots();

	public List<Category> findChildren(int id);

	public List<Category> findByTitle(String title);
	
}
