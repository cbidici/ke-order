package com.ke.order.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for index.html page.
 * @author cbidici
 * @since 0.0.1 (20141220)
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping
	public String getIndex(Model model) {
		// set page for setting top menu acitve state
		model.addAttribute("page","index");
		
		// view index.jsp file
		return "index";
	}
}
