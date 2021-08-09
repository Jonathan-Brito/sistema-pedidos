package com.brito.sistemapedidos.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.brito.sistemapedidos.domain.Category;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Campo name é requerido")
	@Length(min = 3, max = 100, message = "Campo name deve ter mais de 3 caracteres")
	private String name;

	public CategoryDTO() {
		super();

	}

	public CategoryDTO(Category category) {
		super();
		this.id = category.getId();
		this.name = category.getName();

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

}
