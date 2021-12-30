package com.brito.sistemapedidos.resources;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brito.sistemapedidos.domain.Request;
import com.brito.sistemapedidos.services.RequestService;


@RestController
@RequestMapping(value = "/requests")
public class RequestResource {
	
	@Autowired
	private RequestService requestService;
	
	/*@GetMapping
	public ResponseEntity<List<Request>> findAll(){
		List<Request> list = requestService.findAll();

		return ResponseEntity.ok().body(list);
	}*/
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Request> findById(@PathVariable Integer id){
		
		Request optional = requestService.findById(id);
		return ResponseEntity.ok().body(optional);
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Request request){
		
				
		request = requestService.create(request);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(request.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Request>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "instante") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC") String direction){
			
		Page<Request> list = requestService.findPage(page, linesPerPage, orderBy, direction);
		
		//Page<RequestyDTO> listDto = list.map(obj -> new CategoryDTO(obj));
				

		return ResponseEntity.ok().body(list);
	}
	

}
