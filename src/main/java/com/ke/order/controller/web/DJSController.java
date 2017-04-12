package com.ke.order.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resources/djs")
public class DJSController {
	
	@RequestMapping("/constants")
	public String constants(){
		return "djs/constants";
	}
}
