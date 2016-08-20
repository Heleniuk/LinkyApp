package com.bionic.edu.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Controller
public class ExceptionHandlingController {

	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView handleError(HttpServletRequest req, IllegalArgumentException ex) {
	    
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("errorMessage", ex.getMessage());
	    mav.setViewName("error");
	    return mav;
	    
	  }
}
