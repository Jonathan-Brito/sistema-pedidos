package com.brito.sistemapedidos.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.brito.sistemapedidos.dtos.CategoryDTO;
import com.brito.sistemapedidos.domain.Category;
import com.brito.sistemapedidos.repositories.CategoryRepository;
import com.brito.sistemapedidos.services.exceptions.DataIntegrityException;
import com.brito.sistemapedidos.services.exceptions.ObjectNotFoundException;


@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
	
	public Category create(Category category) {
		category.setId(null);
		
		return categoryRepository.save(category);
	}
	
	public Category update(Integer id, CategoryDTO categoryDTO) {
		Category category = findById(id);
		
		category.setName(categoryDTO.getName());
				
		return categoryRepository.save(category);
	}
	
	public void delete(Integer id) {
		findById(id);
		
		try {
			categoryRepository.deleteById(id);
		} 
		catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityException
			("Category não pode ser deletado! Possui products associados");
		}
	}

}
