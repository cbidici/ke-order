package com.ke.order.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Sale Item. This class supports many to many relationed tree on itself.
 * By this feature, it is possible to keep any hierarchical sale item information in any depth. 
 * @author cbidici
 * @since 0.0.1 (20141219)
 */
@Entity
@Table(name = "ke_sale_item")
public class SaleItem extends Base {

	private static final long serialVersionUID = 6635644583985735775L;

	// TODO : Think about adding index
	private Integer level;

	private String name;

	private Float price;

	@ManyToMany
	@JoinTable(name = "ke_sale_item_parent_rel", 
		joinColumns = { @JoinColumn(name = "child_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "parent_id") })
	private List<SaleItem> parents;

	@ManyToMany(mappedBy="parents")
	private List<SaleItem> children;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	// We must avoid displaying parents in Json formattet output.
	// Because during creation of JSon output this method is being called and it may cause recursive calls.
	// Also we should consider about hibernate session because of lazy initialization
	@JsonIgnore
	public List<SaleItem> getParents() {
		return parents;
	}

	public void setParents(List<SaleItem> parents) {
		this.parents = parents;
	}

	// We must avoid displaying children in Json formattet output.
	// Because during creation of JSon output this method is being called and it may cause recursive calls.
	// Also we should consider about hibernate session because of lazy initialization
	@JsonIgnore
	public List<SaleItem> getChildren() {
		return children;
	}

	public void setChildren(List<SaleItem> children) {
		this.children = children;
	}

}
