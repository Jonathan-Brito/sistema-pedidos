package com.brito.sistemapedidos.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.brito.sistemapedidos.domain.Category;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	// id de categira
	private Integer id;

	@NotEmpty(message = "Campo name é obrigatório")
	@Length(min = 5, max = 100, message = "Campo name deve ter entre 5 e 100 caracteres")
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
