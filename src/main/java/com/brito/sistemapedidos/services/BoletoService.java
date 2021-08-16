package com.brito.sistemapedidos.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.brito.sistemapedidos.domain.PaymentWithBillet;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PaymentWithBillet pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}

}
