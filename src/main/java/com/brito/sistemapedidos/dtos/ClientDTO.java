package com.brito.sistemapedidos.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.brito.sistemapedidos.domain.Client;
import com.brito.sistemapedidos.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Campo name é obrigatório")
	@Length(min = 5, max = 100, message = "Campo name deve ter entre 5 e 100 caracteres")
	private String name;
	
	@NotEmpty(message = "Campo email é obrigatório")
	@Email(message = "Email é inválido")
	private String email;

	public ClientDTO() {
		
	}
	
	public ClientDTO(Client client) {
		id = client.getId();
		name = client.getName();
		email = client.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
