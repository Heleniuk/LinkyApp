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

import com.bionic.edu.proc.entities.Link;
import com.bionic.edu.proc.formmodels.LinkFormModel;
import com.bionic.edu.proc.services.CategoryService;
import com.bionic.edu.proc.services.LinkService;

@Controller
@RequestMapping("/links")
public class LinksController {
    
	@Inject
    LinkService linkService;
	@Inject
	CategoryService categoryService;
	
	
	@RequestMapping(value="/default",method = RequestMethod.GET)   
    public ModelAndView defaultSearch() {
		
		ModelAndView mav = new ModelAndView();		
		//initialize current content to display
		mav.addObject("searchResults", linkService.getSortedByRating());        
		mav.addObject("categories", categoryService.findAll());
		
		//tie objects to the forms
		mav.addObject("linkFormModel", new LinkFormModel());
        
        //which forms to display
		mav.addObject("add", false);
		mav.addObject("update", false);
		mav.addObject("search", true);
		mav.setViewName("links");		
        return mav;
        
    }
	
	@RequestMapping(value="/search", method = RequestMethod.GET)   
    public ModelAndView search(@ModelAttribute("linkFormModel") LinkFormModel linkFormModel) {
		
		ModelAndView mav = new ModelAndView();
		//initialize current content to display
		mav.addObject("searchResults", linkService.search(linkFormModel));        
		mav.addObject("categories", categoryService.findAll());
		
		//tie objects to the forms
		mav.addObject("linkFormModel", linkFormModel);
		mav.addObject("linkFormModelToUpdate", new LinkFormModel());
		
		//which forms to display
		mav.addObject("add", false);
		mav.addObject("update", false);
		mav.addObject("search", true);
		mav.setViewName("links");        
		return mav;
        
    }	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView passToAddForm() {
	
		 ModelAndView mav = new ModelAndView();
		 //initialize current content to display
		 mav.addObject("searchResults", linkService.getSortedByRating());
		 mav.addObject("categories", categoryService.findAll());
	     
	     //tie objects to the forms
		 mav.addObject("linkFormModel", new LinkFormModel());
		 mav.addObject("linkFormModelToUpdate", new LinkFormModel());
	     
	     //which forms to display
		 mav.addObject("add", true);
		 mav.addObject("update", false);
		 mav.addObject("search", false);
	     mav.setViewName("links");	     	     
	     return mav;
	     
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ModelAndView addLink(@Valid @ModelAttribute("linkFormModel") LinkFormModel linkFormModel,
			BindingResult bindingResult) {
		
		ModelAndView mav = new ModelAndView();		
		if(bindingResult.hasErrors()) {			
		     
			 //initialize current content to display
			 mav.addObject("searchResults", linkService.getSortedByRating());
			 mav.addObject("categories", categoryService.findAll());
		     
		     //tie objects to the forms
			 mav.addObject("linkFormModelToUpdate", new LinkFormModel());
		     
		     //which forms to display
			 mav.addObject("add", true);
			 mav.addObject("update", false);
			 mav.addObject("search", false);
		     mav.setViewName("links");		     
		     return mav;
			
		} else {
			linkService.add(linkFormModel); 
			mav.setViewName("redirect:/links/default");
			return mav;
		}
		     
	 }
	
	@RequestMapping(value = "/delete/{linkId}", method = RequestMethod.POST)
	public ModelAndView deleteLink(@PathVariable String linkId) {
		 ModelAndView mav = new ModelAndView();
	     linkService.delete(Integer.valueOf(linkId));
	     mav.setViewName("redirect:/links/default");
	     return mav;
	}
	
	@RequestMapping(value = "/edit/{linkId}", method = RequestMethod.GET)
	public ModelAndView passToUpdateForm(@PathVariable String linkId) {
		
		 ModelAndView mav = new ModelAndView();
		 //initialize current content to display
		 mav.addObject("searchResults", linkService.getSortedByRating());
		 mav.addObject("categories", categoryService.findAll());
	     
	     //tie objects to the forms
		 mav.addObject("linkFormModel", new LinkFormModel());
	     Link linkToUpdate = linkService.findById(Integer.parseInt(linkId));
	     mav.addObject("linkFormModelToUpdate", new LinkFormModel(linkToUpdate));
	     
	     //which forms to display
	     mav.addObject("add", false);
	     mav.addObject("update", true);
	     mav.addObject("search", false);
	     mav.setViewName("links");	     
	     return mav;
	     
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView updateLink(@Valid @ModelAttribute("linkFormModelToUpdate") LinkFormModel linkFormModel,
			BindingResult bindingResult) {
		
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
					     
			//initialize current content to display		     
			 mav.addObject("searchResults", linkService.getSortedByRating());
			 mav.addObject("categories", categoryService.findAll());
		     
		     //tie objects to the forms
			 mav.addObject("linkFormModel", new LinkFormModel());
		     
		     //which forms to display
			 mav.addObject("add", false);
			 mav.addObject("update", true);
			 mav.addObject("search", false);
		     mav.setViewName("links");		     
		     return mav;
			 
		} else {
			linkService.update(linkFormModel); 
			mav.setViewName("redirect:/links/default");
			return mav;
		}
		     
	 }
 }
