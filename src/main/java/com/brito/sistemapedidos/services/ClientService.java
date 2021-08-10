package com.brito.sistemapedidos.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import com.brito.sistemapedidos.domain.Client;
import com.brito.sistemapedidos.dtos.ClientDTO;
import com.brito.sistemapedidos.repositories.ClientRepository;
import com.brito.sistemapedidos.services.exceptions.DataIntegrityException;
import com.brito.sistemapedidos.services.exceptions.ObjectNotFoundException;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public List<Client> findAll(){
		return clientRepository.findAll();
	}
	
	public Client findById(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}
	
	public Client create(Client client) {
		client.setId(null);
		
		return clientRepository.save(client);
	}
	
	public Client update(Integer id, ClientDTO clientDTO) {
		Client client = findById(id);
		
		client.setName(clientDTO.getName());
				
		return clientRepository.save(client);
	}
	
	public void delete(Integer id) {
		findById(id);
		
		try {
			clientRepository.deleteById(id);
		} 
		catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityException
			("Client não pode ser deletado! Possui products associados");
		}
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
	}
	
	

}
