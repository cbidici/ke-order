package com.ke.order.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ke.order.entity.Order;
import com.ke.order.entity.OrderItem;
import com.ke.order.repository.OrderItemRepository;
import com.ke.order.repository.OrderRepository;
import com.ke.order.service.OrderService;

/**
 * Service class to handle order related operations
 * @author cbidici
 * @since 0.0.1 (20141920)
 */
@Service
public class OrderServiceCommonImpl implements OrderService {

	private static final Logger logger = Logger.getLogger(OrderServiceCommonImpl.class);
	
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	/**
	 * Common implementation for saving order
	 * @author cbidici
	 * @since 0.0.1 (20141220)
	 */
	@Transactional
	@Override
	public void saveOrder(Order order) {

		// save order to db
		orderRepository.save(order);
		logger.debug("Order saved: " + order);
		
		// set order references of directly connected order items
		for(OrderItem tmp : order.getOrderItems()){
			tmp.setOrder(order);
		}
		
		// save order items in a recusive way with it's children
		saveOrderItemsRec(order.getOrderItems(), null);
	}

	/**
	 * Save given order items in a recursive way with it's children 
	 * @param orderItems order item list to insert db
	 * @param parentOrderItem parent order item to set parent references of child order items
	 * @author cbidici
	 * @since 0.0.1 (20141220)
	 */
	private void saveOrderItemsRec(List<OrderItem> orderItems, OrderItem parentOrderItem) {
		if (null != orderItems) {
			
			// set parent of child order items
			for(OrderItem tmp : orderItems) {
				tmp.setParent(parentOrderItem);
			}
			
			// save order items to dabase
			orderItemRepository.save(orderItems);
			logger.debug("Order Items saved: " + orderItems);
			
			for(OrderItem tmp : orderItems) {
				// call same method to do the same operation for child order items
				saveOrderItemsRec(tmp.getChildren(), tmp);
			}
			
		} else {
			return;
		}
	}

	/**
	 * Common implementation for calculating total price of order
	 * Load order form db and calculate total price
	 * @param order to be calculated
	 * @author cbidici
	 * @since 0.0.1 (20141220)
	 */
	@Transactional
	@Override
	public void calcTotalPrice(Order order) {
		
		// Load order with it's order items and sale itmes.
		Order orderDB = orderRepository.findOne(order.getId());
		
		// Calculate total price in a recursive way
		order.setTotalPrice(calcPriceRec(orderDB.getOrderItems()));
	}
	
	/**
	 * Calculate price with recursion on order items and child order items
	 * @param orderItems order items of order to calculate of it's total price
	 * @return Float total price
	 */
	private Float calcPriceRec(List<OrderItem> orderItems){
		
		// Start with 0 price
		Float orItemPrice = 0f;
		
		// For each order item
		for (OrderItem tmp : orderItems) {
			
			// First calculate price for current order item. Because it also contains sale item.
			orItemPrice = orItemPrice
					+ (tmp.getSaleItem().getPrice() * tmp.getItemCount());
			
			// Calculate child order item prices and add current recursion calculated price
			orItemPrice = orItemPrice + calcPriceRec(tmp.getChildren());
		}
		
		return orItemPrice;
	}

}
