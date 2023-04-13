package com.devsuperior.dscatalog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;

/* Componente que participa da Injeção de Dependência */
@Service 
public class CategoryService {
	
	/*Informa que objeto será gerenciado pelo Spring */
	@Autowired
	private CategoryRepository repository;	
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		
		
		List<CategoryDTO> listDTO = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		/* 
		 List<CategoryDTO> listDTO = new ArrayList<>();
		 
		
		for(Category cat: list) {
			listDTO.add(new CategoryDTO(cat));
		}
		
		*/
		return listDTO;
	}

}
