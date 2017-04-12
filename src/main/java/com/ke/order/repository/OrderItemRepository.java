package com.ke.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ke.order.entity.OrderItem;

/**
 * Default order item repository interface
 * @author cbidici
 * @since 0.0.1 (20141219)
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
