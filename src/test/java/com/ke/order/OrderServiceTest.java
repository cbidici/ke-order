package com.ke.order;


import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ke.order.controller.api.SaleItemApiController;
import com.ke.order.entity.Order;
import com.ke.order.entity.OrderItem;
import com.ke.order.entity.SaleItem;
import com.ke.order.service.OrderService;

/**
 * Test case for order service
 * @author cbidici
 * @since 0.0.1 (20141220)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext.xml", "dispatcher-servlet.xml" })
public class OrderServiceTest {

	@Autowired
	OrderService orderService;
	
	@Autowired
	SaleItemApiController saleItemApiController;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReceiveOrder() {
		
		// Find tea from DB
		List<SaleItem> saleItems = saleItemApiController.getSaleItemsByLevel(1);
		SaleItem tea = null;
		
		for(SaleItem saleItem : saleItems)
		{
			if(saleItem.getName().equals("Çay"))
			{
				tea = saleItem;
				break;
			}
		}
			 
		//Insert order
		OrderItem orderItem = new OrderItem();
		orderItem.setItemCount(1);
		orderItem.setSaleItem(tea);

		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(orderItem);
		
		Order order = new Order();
		order.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		order.setOrderItems(orderItems);
		
		orderService.saveOrder(order);
		
		// Calculate price
		orderService.calcTotalPrice(order);
		
		// Check
		assertEquals(3f, order.getTotalPrice(), 0f);
		
	}

}
