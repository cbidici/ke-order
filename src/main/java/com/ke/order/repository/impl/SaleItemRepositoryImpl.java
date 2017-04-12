package com.ke.order.repository.impl;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ke.order.entity.SaleItem;
import com.ke.order.repository.SaleItemCustomRepository;
import com.ke.order.repository.SaleItemRepository;

/**
 * Custom interface for sale item repository
 * @author cbidici
 * @since 0.0.1 (20141219)
 */
public class SaleItemRepositoryImpl implements SaleItemCustomRepository {

	@Autowired
	SaleItemRepository saleItemRepository;
	
	@Autowired
	SessionFactory sessionFactory;
	
	/**
	 * Common implementation for implemented interface
	 */
	@Override
	public SaleItem loadSaleItemById(Long id) {
		
		// find sale item by id
		SaleItem saleItem = saleItemRepository.findOne(id);
		
		// lazy initialize parents
		Hibernate.initialize(saleItem.getParents());
		
		// lazy initialize children
		Hibernate.initialize(saleItem.getChildren());
		return saleItem;
	}
	
}
