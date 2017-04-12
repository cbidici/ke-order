package com.ke.order;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ke.order.controller.api.SaleItemApiController;
import com.ke.order.entity.SaleItem;

/**
 * Test case for sale item api controller
 * @author cbidici
 * @since 0.0.1 (20141220)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext.xml", "dispatcher-servlet.xml" })
public class SaleItemApiControllerTest {

	@Autowired
	SaleItemApiController saleItemApiController;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetSaleItemsByLevel() {
		
		// Test beverages
		List<SaleItem> saleItems = saleItemApiController.getSaleItemsByLevel(1);
		assertEquals(4, saleItems.size());
		
		// Test additions
		saleItems = saleItemApiController.getSaleItemsByLevel(2);
		assertEquals(3, saleItems.size());
	}
	
	@Test
	public void testGetSaleItemChildren() {
		// Get sale items
		List<SaleItem> saleItems = saleItemApiController.getSaleItemsByLevel(1);
		
		// Any of them should have 3 additions, check only for one
		List<SaleItem> childSaleItems = saleItemApiController.getSaleItemChildren(saleItems.get(0).getId());
		assertEquals(3, childSaleItems.size());
	}

}
