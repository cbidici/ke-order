package com.ke.order.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Wrapper class for order items. It also contains detailed information in order level
 * @author cbidici
 * @since 0.0.1 (20141220)
 */
@Entity
@Table(name = "ke_order")
public class Order extends Base {

	private static final long serialVersionUID = 3202066422006359602L;

	@Column(name = "create_date")
	private Timestamp createDate;

	// Need to fetch order items of order immediately. It is convenient according to business logic.
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private List<OrderItem> orderItems;
	
	// totalPrice is not located in database in order table. It is being calculated after query
	@Transient
	private Float totalPrice;

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
