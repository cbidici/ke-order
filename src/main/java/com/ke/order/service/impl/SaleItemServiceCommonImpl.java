package com.ke.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ke.order.entity.SaleItem;
import com.ke.order.repository.SaleItemRepository;
import com.ke.order.service.SaleItemService;

/**
 * Common implementation for SaleItemService
 * @author cbidici
 * @since 0.0.1 (20141219)
 */
@Service
public class SaleItemServiceCommonImpl implements SaleItemService {

	@Autowired
	SaleItemRepository saleItemRepository;
	
	/**
	 * Common implementation of method for implemented interface
	 * @param id of sale item
	 * @return SaleItem list which are children of parent sale item with given level id
	 * @author cbidici
	 * @since 0.0.1 (20141219)
	 */
	@Transactional
	@Override
	public List<SaleItem> getSaleItemChildren(Long id) {
		// Load sale item from database by Id
		SaleItem saleItem = saleItemRepository.loadSaleItemById(id);
		
		return saleItem.getChildren();
	}
	
	/**
	 * Common implementation of method for implemented interface
	 * @param level of sale item
	 * @return Return list of sale items in given level
	 * @author cbidici
	 * @since 0.0.1 (20141219)
	 */
	@Override
	public List<SaleItem> getSaleItemByLevel(Integer level){
		return saleItemRepository.findByLevel(level);
	}

}
