package com.brito.sistemapedidos.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.brito.sistemapedidos.domain.enums.StatePayment;

@Entity
public class PaymentWithBillet extends Payment {
	
	private static final long serialVersionUID = 1L;

	private Date dataVencimento;
	
	private Date dataPagamento;
	
	public PaymentWithBillet() {
		
	}

	public PaymentWithBillet(Integer id, StatePayment state, Request request,
			Date dataVencimento, Date dataPagamento) {
		super(id, state, request);
		
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		
		
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}
