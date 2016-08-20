package com.bionic.edu.web;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bionic.edu.proc.entities.Category;
import com.bionic.edu.proc.formmodels.CategoryFormModel;
import com.bionic.edu.proc.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

	@Inject
	CategoryService categoryService;

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public ModelAndView showCategories() {

		ModelAndView mav = new ModelAndView();
		mav.addObject("roots", categoryService.getTreeRoots());
		mav.addObject("parentCategories", categoryService.findAll());
		mav.addObject("categoryFormModel", new CategoryFormModel());
		mav.addObject("categoryFormModelToUpdate", new CategoryFormModel());

		// which forms to display
		mav.addObject("add", true);
		mav.addObject("update", false);
		mav.setViewName("categories");
		return mav;

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addCategory(@Valid @ModelAttribute("categoryFormModel") CategoryFormModel categoryFormModel,
			BindingResult bindingResult) {

		ModelAndView mav = new ModelAndView();

		if (bindingResult.hasErrors()) {

			mav.addObject("roots", categoryService.getTreeRoots());
			mav.addObject("parentCategories", categoryService.findAll());
			mav.addObject("categoryFormModelToUpdate", new CategoryFormModel());

			// which forms to display
			mav.addObject("add", true);
			mav.addObject("update", false);
			mav.setViewName("categories");
			return mav;

		} else {

			categoryService.add(categoryFormModel);
			mav.addObject("categoryFormModel", new CategoryFormModel());
			mav.setViewName("redirect:/categories/default");
			return mav;

		}

	}

	@RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.POST)
	public ModelAndView deleteCategory(@PathVariable String categoryId) {

		ModelAndView mav = new ModelAndView();
		int id = Integer.valueOf(categoryId);
		categoryService.delete(id);
		mav.setViewName("redirect:/categories/default");
		return mav;

	}

	@RequestMapping(value = "/edit/{categoryId}", method = RequestMethod.GET)
	public ModelAndView passToUpdateForm(@PathVariable String categoryId) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("roots", categoryService.getTreeRoots());
		mav.addObject("parentCategories", categoryService.findAll());
		mav.addObject("categoryFormModel", new CategoryFormModel());

		// fill the update form with current parameters of the category to
		// update
		Category categoryToUpdate = categoryService.findById(Integer.valueOf(categoryId));
		mav.addObject("categoryFormModelToUpdate", new CategoryFormModel(categoryToUpdate));

		// which forms to display
		mav.addObject("add", false);
		mav.addObject("update", true);
		mav.setViewName("categories");
		return mav;

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateCategory(
			@Valid @ModelAttribute("categoryFormModelToUpdate") CategoryFormModel categoryFormModel,
			BindingResult bindingResult) {

		ModelAndView mav = new ModelAndView();

		if (bindingResult.hasErrors()) {

			mav.addObject("roots", categoryService.getTreeRoots());
			mav.addObject("parentCategories", categoryService.findAll());
			mav.addObject("categoryFormModel", new CategoryFormModel());

			// which forms to display
			mav.addObject("add", false);
			mav.addObject("update", true);
			mav.setViewName("categories");
			return mav;

		} else {

			categoryService.update(categoryFormModel);
			mav.setViewName("redirect:/categories/default");
			return mav;

		}

	}

}
