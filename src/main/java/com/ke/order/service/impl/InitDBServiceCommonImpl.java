package com.ke.order.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ke.order.entity.Order;
import com.ke.order.entity.OrderItem;
import com.ke.order.entity.SaleItem;
import com.ke.order.repository.SaleItemRepository;
import com.ke.order.service.InitDBService;
import com.ke.order.service.OrderService;

/**
 * Prepare database for run.
 * @author cbidici
 * @since 0.0.1 (20141220)
 */
@Service
public class InitDBServiceCommonImpl implements InitDBService{

	private static final Logger logger = Logger.getLogger(InitDBServiceCommonImpl.class);
	
	@Autowired
	private SaleItemRepository saleItemRepository;
	
	@Autowired
	private OrderService orderService;

	/**
	 * Common implementation for InitDBService.initDataBase method.
	 */
	@PostConstruct
	@Transactional
	@Override
	public void initDataBase() {
		
		SaleItem root = new SaleItem();
		root.setLevel(0);
		root.setName("ROOT");
		saleItemRepository.save(root);
		
		List<SaleItem> rootParentSet = new ArrayList<SaleItem>();
		rootParentSet.add(root);
		
		SaleItem filteredCoffee = new SaleItem();
		filteredCoffee.setLevel(1);
		filteredCoffee.setName("Filtre Kahve");
		filteredCoffee.setPrice(4f);
		filteredCoffee.setParents(rootParentSet);
		saleItemRepository.save(filteredCoffee);
		
		SaleItem latte = new SaleItem();
		latte.setLevel(1);
		latte.setName("Latte");
		latte.setPrice(5f);
		latte.setParents(rootParentSet);
		saleItemRepository.save(latte);
		
		SaleItem mocha = new SaleItem();
		mocha.setLevel(1);
		mocha.setName("Mocha");
		mocha.setPrice(6f);
		mocha.setParents(rootParentSet);
		saleItemRepository.save(mocha);
		
		SaleItem tea = new SaleItem();
		tea.setLevel(1);
		tea.setName("Çay");
		tea.setPrice(3f);
		tea.setParents(rootParentSet);
		saleItemRepository.save(tea);
		
		List<SaleItem> allCoffeeSet = new ArrayList<SaleItem>();
		allCoffeeSet.add(filteredCoffee);
		allCoffeeSet.add(latte);
		allCoffeeSet.add(mocha);

		SaleItem milk = new SaleItem();
		milk.setLevel(2);
		milk.setName("Süt");
		milk.setPrice(2f);
		milk.setParents(allCoffeeSet);
		saleItemRepository.save(milk);
		
		SaleItem hazelnut = new SaleItem();
		hazelnut.setLevel(2);
		hazelnut.setName("Fındık Şurubu");
		hazelnut.setPrice(3f);
		hazelnut.setParents(allCoffeeSet);
		saleItemRepository.save(hazelnut);
		
		SaleItem chocolate = new SaleItem();
		chocolate.setLevel(2);
		chocolate.setName("Çikolata Sosu");
		chocolate.setPrice(5f);
		chocolate.setParents(allCoffeeSet);
		saleItemRepository.save(chocolate);
		
		//Insert order example
		OrderItem orderItem = new OrderItem();
		orderItem.setItemCount(1);
		orderItem.setSaleItem(mocha);

		OrderItem milkOrderItem = new OrderItem();
		milkOrderItem.setItemCount(2);
		milkOrderItem.setSaleItem(milk);
		//milkOrderItem.setParent(orderItem);

		OrderItem hazelOrderItem = new OrderItem();
		hazelOrderItem.setItemCount(1);
		hazelOrderItem.setSaleItem(hazelnut);
		//hazelOrderItem.setParent(orderItem);
		
		List<OrderItem> children = new ArrayList<OrderItem>();
		children.add(hazelOrderItem);
		children.add(milkOrderItem);
		orderItem.setChildren(children);
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(orderItem);
		
		Order order = new Order();
		order.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		order.setOrderItems(orderItems);
		
		orderService.saveOrder(order);
		
		logger.debug("DATABASE INITIALIZED");
	}

}
