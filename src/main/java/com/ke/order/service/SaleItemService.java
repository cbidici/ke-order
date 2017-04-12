package com.ke.order.service;

import java.util.List;

import com.ke.order.entity.SaleItem;
import com.ke.order.service.impl.SaleItemServiceCommonImpl;

/**
 * Interface for all sale item services
 * @author cbidici
 * @since 0.0.1 (20141219)
 * @see SaleItemServiceCommonImpl
 */
public interface SaleItemService {

	/**
	 * Get children of sale item
	 * @param id Id of the requested sale item
	 * @return SaleItem list which are children of parent salte item with id 
	 * @author cbidici
	 * @since 0.0.1 (20141219)
	 */
	public List<SaleItem> getSaleItemChildren(Long id);
	
	/**
	 * Get sale items by level
	 * @param level Query level
	 * @return Return list of sale items in given level
	 * @author cbidici
	 * @since 0.0.1 (20141219)
	 */
	public List<SaleItem> getSaleItemByLevel(Integer level);

}
