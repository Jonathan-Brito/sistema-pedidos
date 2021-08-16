package com.brito.sistemapedidos.services;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brito.sistemapedidos.domain.OrderedItem;
import com.brito.sistemapedidos.domain.PaymentWithBillet;
import com.brito.sistemapedidos.domain.Request;
import com.brito.sistemapedidos.domain.enums.StatePayment;
import com.brito.sistemapedidos.repositories.OrderedItemRepository;
import com.brito.sistemapedidos.repositories.PaymentRepository;
import com.brito.sistemapedidos.repositories.RequestRepository;
import com.brito.sistemapedidos.services.exceptions.ObjectNotFoundException;


@Service
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderedItemRepository orderedItemRepository;
	
	public List<Request> findAll(){
		return requestRepository.findAll();
	}
	
	public Request findById(Integer id) {
		Optional<Request> obj = requestRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Request.class.getName()));
	}
	
	public Request create(Request request) {
		request.setId(null);
		
		request.setInstante(new Date());
		request.getPayment().setState(StatePayment.PENDENTE);
		request.getPayment().setRequest(request);
		
		if(request.getPayment() instanceof PaymentWithBillet) {
			PaymentWithBillet pagto = (PaymentWithBillet) request.getPayment();
			boletoService.preencherPagamentoComBoleto(pagto, request.getInstante());
		}
		
		request = requestRepository.save(request);
		paymentRepository.save(request.getPayment());
		
		for(OrderedItem ip : request.getItens() ) {
			ip.setDiscount(0.0);
			ip.setProduct(productService.findById(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setRequest(request);
		}
		
		orderedItemRepository.saveAll(request.getItens());
		return request;
	}
	

}
