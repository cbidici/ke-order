package com.ke.order.controller.api;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ke.order.entity.Order;
import com.ke.order.entity.OrderItem;
import com.ke.order.service.OrderService;
/**
 * Order rest api controller
 * @author cbidici
 * @since 0.0.1 (20141220)
 */
@RestController
@RequestMapping("/api/order")
public class OrderApiController extends BaseApiController {

	private static final Logger logger = Logger.getLogger(OrderApiController.class);
	
	@Autowired
	OrderService orderService;
	
	/**
	 * Request mapping for receiving orders. Only accepts post methods
	 * @param orderItems JSON formatted order item list
	 * @return order prepared by request
	 * @author cbidici
	 * @since 0.0.1 (20141220)
	 */
	@RequestMapping(value = "/receive", method = RequestMethod.POST)
	public Order receiveOrder(@RequestBody List<OrderItem> orderItems)
	{
		// log received order items
		logger.debug("Order Received with items: " + orderItems);

		// prepare order to wrap order items
		Order order = new Order();
		order.setOrderItems(orderItems);
		order.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		// save order
		orderService.saveOrder(order);
		
		// calculate total price of order
		orderService.calcTotalPrice(order);
		
		return order;
	}
	
}
