package com.brito.sistemapedidos.services;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.brito.sistemapedidos.domain.Client;
import com.brito.sistemapedidos.domain.OrderedItem;
import com.brito.sistemapedidos.domain.PaymentWithBillet;
import com.brito.sistemapedidos.domain.Request;
import com.brito.sistemapedidos.domain.enums.StatePayment;
import com.brito.sistemapedidos.repositories.OrderedItemRepository;
import com.brito.sistemapedidos.repositories.PaymentRepository;
import com.brito.sistemapedidos.repositories.RequestRepository;
import com.brito.sistemapedidos.security.UserSS;
import com.brito.sistemapedidos.services.exceptions.AuthorizationException;
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
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ClientService clientService;
	
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
		request.setClient(clientService.findById(request.getClient().getId()));
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
		emailService.sendOrderConfirmationHtmlEmail(request);
		return request;
	}
	
	public Page<Request> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client = clientService.find(user.getId());
		
		return requestRepository.findByClient(client, pageRequest);
		
	}
	

}
