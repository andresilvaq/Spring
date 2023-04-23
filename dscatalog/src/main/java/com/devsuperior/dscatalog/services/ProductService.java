package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

/* Componente que participa da Injeção de Dependência */
@Service
public class ProductService {

	/* Informa que objeto será gerenciado pelo Spring */
	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAll();

		List<ProductDTO> listDTO = list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		/*
		 * List<ProductDTO> listDTO = new ArrayList<>();
		 * 
		 * 
		 * for(Product cat: list) { listDTO.add(new ProductDTO(cat)); }
		 * 
		 */
		return listDTO;
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));

		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		//entity.setName(dto.getName());
		entity = repository.save(entity);

		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(id);
			//entity.setName(dto.getName());
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não localizado: " + id);
		}
	}

	public void delete(Long id) {
		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);
			} else {
				throw new ResourceNotFoundException("Id não localizado: " + id);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não localizado: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação da Integridade ao excluir a categoria. " + id);
		}

	}

	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);

		// Para cada X no Map add na lista instanciando nova ProductDTO
		Page<ProductDTO> listDTO = list.map(x -> new ProductDTO(x));

		return listDTO;
	}

}
