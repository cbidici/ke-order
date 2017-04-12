package com.ke.order.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * OrderItem class to keep ordered sale item records.
 * It contains user generated combinations of sale items.
 * @author cbidici
 * @since 0.0.1 (20141220)
 */
@Entity
@Table(name = "ke_order_item")
public class OrderItem extends Base {

	private static final long serialVersionUID = 4581302655942065947L;

	@Column(name = "item_count")
	private Integer itemCount;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	// Sale item of order item may fetched immediately because it has one to one relation
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sale_item_id")
	private SaleItem saleItem;
	
	@ManyToOne
	private OrderItem parent;

	@OneToMany(mappedBy = "parent")
	private List<OrderItem> children;

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public SaleItem getSaleItem() {
		return saleItem;
	}

	public void setSaleItem(SaleItem saleItem) {
		this.saleItem = saleItem;
	}

	@JsonIgnore
	public OrderItem getParent() {
		return parent;
	}

	public void setParent(OrderItem parent) {
		this.parent = parent;
	}

	public List<OrderItem> getChildren() {
		return children;
	}

	public void setChildren(List<OrderItem> children) {
		this.children = children;
	}

}
