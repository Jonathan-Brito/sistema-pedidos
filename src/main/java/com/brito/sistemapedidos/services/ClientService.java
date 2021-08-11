package com.brito.sistemapedidos.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brito.sistemapedidos.domain.Address;
import com.brito.sistemapedidos.domain.City;
import com.brito.sistemapedidos.domain.Client;
import com.brito.sistemapedidos.domain.enums.TipoClient;
import com.brito.sistemapedidos.dtos.ClientDTO;
import com.brito.sistemapedidos.dtos.ClientNewDTO;
import com.brito.sistemapedidos.repositories.AddressRepository;
import com.brito.sistemapedidos.repositories.ClientRepository;
import com.brito.sistemapedidos.services.exceptions.DataIntegrityException;
import com.brito.sistemapedidos.services.exceptions.ObjectNotFoundException;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public List<Client> findAll(){
		return clientRepository.findAll();
	}
	
	public Client findById(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}
	
	@Transactional
	public Client create(Client client) {
		client.setId(null);
	
		client = clientRepository.save(client);
		
		addressRepository.saveAll(client.getAddresses());
		
		return client;
	}
	
	public Client update(Integer id, ClientDTO clientDTO) {
		Client client = findById(id);
		
		client.setName(clientDTO.getName());
		client.setEmail(clientDTO.getEmail());
				
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
	
	public Client fromDTO(ClientNewDTO clientNewDTO) {
		 Client cli = new Client(null, clientNewDTO.getName(), 
				 clientNewDTO.getEmail(), clientNewDTO.getCpfOrCnpj(), 
				 TipoClient.toEnum(clientNewDTO.getTipo()));
		 
		 City cid = new City(clientNewDTO.getCityId(), null, null);
		 
		 Address end = new Address(null, clientNewDTO.getLogradouro(), clientNewDTO.getNumber(),
				 clientNewDTO.getComplement(), clientNewDTO.getDistrict(),
				 clientNewDTO.getCep(), cli, cid);
		 
		 cli.getAddresses().add(end);
		 cli.getPhones().add(clientNewDTO.getPhone1());
		 
		 if(clientNewDTO.getPhone2() != null) {
			 cli.getPhones().add(clientNewDTO.getPhone2());
		 }
	
		 if(clientNewDTO.getPhone3() != null) {
			 cli.getPhones().add(clientNewDTO.getPhone3());
		 }
		 
		 return cli;

}
}
