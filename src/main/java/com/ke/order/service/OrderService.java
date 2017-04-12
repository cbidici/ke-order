package com.ke.order.service;

import com.ke.order.entity.Order;

/**
 * Interface to be implemented to handle order related operations
 * @author cbidici
 * @since 0.0.1 (20141920)
 */
public interface OrderService {
	
	/**
	 * Method signature for saving order
	 * @param order to be saved
	 * @author cbidici
	 * @since 0.0.1 (20141220)
	 */
	public void saveOrder(Order order);

	/**
	 * Method signature to for calculation of order total price
	 * @param order
	 * @author cbidici
	 * @since 0.0.1 (20141220)
	 */
	public void calcTotalPrice(Order order);
	
}
