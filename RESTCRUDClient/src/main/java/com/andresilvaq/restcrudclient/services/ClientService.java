package com.andresilvaq.restcrudclient.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresilvaq.restcrudclient.dto.ClientDTO;
import com.andresilvaq.restcrudclient.entities.Client;
import com.andresilvaq.restcrudclient.repositories.ClientRepository;
import com.andresilvaq.restcrudclient.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll () {
		List<Client> list = repository.findAll();
		
		List<ClientDTO> listDTO = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
		
		return listDTO;
	}
	
	@Transactional
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
		
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		return new ClientDTO(entity);
	}

	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

}