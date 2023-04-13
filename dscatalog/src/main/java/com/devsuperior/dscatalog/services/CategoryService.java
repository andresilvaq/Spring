package com.devsuperior.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;

/* Componente que participa da Injeção de Dependência */
@Service 
public class CategoryService {
	
	/*Informa que objeto será gerenciado pelo Spring */
	@Autowired
	private CategoryRepository repository;	
	
	@Transactional(readOnly = true)
	public List<Category> findAll() {
		return repository.findAll();
	}

}
