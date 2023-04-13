package com.devsuperior.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;

@RestController
@RequestMapping (value = "/categories") 
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	 
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<CategoryDTO> lista = new ArrayList<>();

//		lista.add(new Category(1L, "Books"));
//		lista.add(new Category(2L, "Electronics"));
		lista = service.findAll();
		
		
		return ResponseEntity.ok().body(lista);
	}
}
