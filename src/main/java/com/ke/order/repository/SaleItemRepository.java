package com.ke.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ke.order.entity.SaleItem;

/**
 * Default sale item repository interface
 * @author cbidici
 * @since 0.0.1 (20141219)
 */
public interface SaleItemRepository extends JpaRepository<SaleItem, Long>, SaleItemCustomRepository {
	
	/**
	 * Find sale items by id. Implementation will be provided by JpaRepository
	 * @param level Level to query.
	 * @return All sale items from given level
	 * @author cbidici
	 * @since 0.0.1 (20141219)
	 */
	public List<SaleItem> findByLevel(Integer level);
	
}
