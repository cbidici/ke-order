package com.ke.order.controller.api;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ke.order.entity.SaleItem;
import com.ke.order.service.SaleItemService;

/**
 * SaleItem rest api controller
 * 
 * @author cbidici
 * @since 0.0.1 (20141219)
 */
@RestController
@RequestMapping("/api/saleitem")
public class SaleItemApiController extends BaseApiController {

	private static final Logger logger = Logger.getLogger(SaleItemApiController.class);
	
	@Autowired
	SaleItemService saleItemService;

	/**
	 * Request handler for getting sale items by id
	 * 
	 * @param id Id of requested sale item
	 * @return Queried sale item with given id
	 * @author cbidici
	 * @since 0.0.1 (20141219)
	 */
	@RequestMapping("/children")
	public List<SaleItem> getSaleItemChildren(@RequestParam Long id) {
		
		logger.debug("Child sale items requested by parent id : Parent Id " + id);
		// Get children of given sale item
		return saleItemService.getSaleItemChildren(id);
	}

	/**
	 * Request handler for providing sale items from requested level
	 * 
	 * @param level of sale items to be queried
	 * @return Queried sale items
	 * @author cbidici
	 * @since 0.0.1 (20141219)
	 */
	@RequestMapping("/level")
	public List<SaleItem> getSaleItemsByLevel(@RequestParam Integer level) {
		
		logger.debug("Sale Items by level requested : Level " + level);
		// get sale items in requested level
		return saleItemService.getSaleItemByLevel(level);
	}
}
