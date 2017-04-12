package com.ke.order.repository;

import com.ke.order.entity.SaleItem;

/**
 * Custom interface for sale item repository
 * @author cbidici
 * @since 0.0.1 (20141219)
 */
public interface SaleItemCustomRepository {

	/**
	 * Load sale item from database by given id. This method initiates parent and child sets of sale item
	 * @param id Sale item id to query
	 * @return loaded sale item
	 */
	public SaleItem loadSaleItemById(Long id);
	
}
