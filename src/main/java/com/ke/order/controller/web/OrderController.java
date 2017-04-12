package com.ke.order.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ke.order.service.SaleItemService;

/**
 * Controller for order.html page
 * @author cbidici
 * @since 0.0.1 (20141220)
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	public static final Integer BEVERAGES_LEVEL = 1;
	
	@Autowired
	SaleItemService saleItemService;
	
	@RequestMapping
	public String getOrder(Model model) {
		// set page for setting top menu acitve state
		model.addAttribute("page", "order");
		
		// set sale itmes in bevereges level to show in order page
		model.addAttribute("saleItems", saleItemService.getSaleItemByLevel(BEVERAGES_LEVEL));

		// view order.jsp file
		return "order";
	}
}
