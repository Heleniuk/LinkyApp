package com.bionic.edu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class DefaultController {

	@RequestMapping(method = RequestMethod.GET)   
    public ModelAndView defaultRedirect() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:links/default");
		return mav;
	}
}
