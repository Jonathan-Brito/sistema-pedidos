package com.brito.sistemapedidos.domain;

import javax.persistence.Entity;

import com.brito.sistemapedidos.domain.enums.StatePayment;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment {
	
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PaymentWithCard() {
		
	}

	public PaymentWithCard(Integer id, StatePayment state, Request request,
			Integer numeroDeParcelas) {
		super(id, state, request);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	

}
