package com.brito.sistemapedidos.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.brito.sistemapedidos.domain.Client;
import com.brito.sistemapedidos.domain.enums.TipoClient;
import com.brito.sistemapedidos.dtos.ClientNewDTO;
import com.brito.sistemapedidos.repositories.ClientRepository;
import com.brito.sistemapedidos.resources.exception.FieldMessage;
import com.brito.sistemapedidos.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoClient.PESSOAFISICA.getCode())
			&& !BR.isValidCPF(objDto.getCpfOrCnpj())){
			list.add(new FieldMessage("cpfOrCnpj ", "CPF inválido"));
		}
		
		if (objDto.getTipo().equals(TipoClient.PESSOAJURIDICA.getCode())
				&& !BR.isValidCNPJ(objDto.getCpfOrCnpj())){
				list.add(new FieldMessage("cpfOrCnpj ", "CNPJ inválido"));
			}
		
		Client aux = clientRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage exception : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(exception.getMessage())
					.addPropertyNode(exception.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
