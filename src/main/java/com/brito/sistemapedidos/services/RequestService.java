package com.brito.sistemapedidos.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brito.sistemapedidos.domain.Request;
import com.brito.sistemapedidos.repositories.RequestRepository;
import com.brito.sistemapedidos.services.exceptions.ObjectNotFoundException;


@Service
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	public List<Request> findAll(){
		return requestRepository.findAll();
	}
	
	public Request findById(Integer id) {
		Optional<Request> obj = requestRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Request.class.getName()));
	}
	

}
