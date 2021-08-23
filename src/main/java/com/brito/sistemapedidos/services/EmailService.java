package com.brito.sistemapedidos.services;

import org.springframework.mail.SimpleMailMessage;

import com.brito.sistemapedidos.domain.Request;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Request obj);
	
	void sendEmail(SimpleMailMessage msg);

}
