package com.brito.sistemapedidos.resources;



import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.brito.sistemapedidos.domain.Client;
import com.brito.sistemapedidos.dtos.ClientDTO;
import com.brito.sistemapedidos.services.ClientService;


@RestController
@RequestMapping(value = "/customers")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(){
		List<Client> list = clientService.findAll();
		
		List<ClientDTO> listDto = list.stream().map(obj -> new ClientDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Integer id){
		
		Client optional = clientService.findById(id);
		return ResponseEntity.ok().body(optional);
	}
	
		
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@Valid @PathVariable Integer id, @RequestBody ClientDTO clientDTO){
		
		Client client = clientService.update(id, clientDTO);
		
		return ResponseEntity.ok().body(new ClientDTO(client));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		clientService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction){
			
		Page<Client> list = clientService.findPage(page, linesPerPage, orderBy, direction);
		
		Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj));
				

		return ResponseEntity.ok().body(listDto);
	}
	
	

}