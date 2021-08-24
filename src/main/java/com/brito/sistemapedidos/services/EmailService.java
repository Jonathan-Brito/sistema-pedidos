package com.brito.sistemapedidos.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.brito.sistemapedidos.domain.Request;

public interface EmailService {
	//Text
	void sendOrderConfirmationEmail(Request obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	//Html
	void sendOrderConfirmationHtmlEmail(Request obj);
	
	void sendHtmlEmail(MimeMessage msg);

}
