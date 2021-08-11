package com.brito.sistemapedidos.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderedItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrderedItemPK id = new OrderedItemPK();

	private Double discount;

	private Integer amount;

	private Double price;

	public OrderedItem() {

	}

	public OrderedItem(Request request, Product product, Double discount, Integer amount, Double price) {
		super();
		id.setRequest(request);
		id.setProduct(product);
		this.discount = discount;
		this.amount = amount;
		this.price = price;
	}

	public OrderedItemPK getId() {
		return id;
	}

	public void setId(OrderedItemPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@JsonIgnore
	public Request getRequest() {
		return id.getRequest();
	}

	public Product getProduct() {
		return id.getProduct();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedItem other = (OrderedItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}