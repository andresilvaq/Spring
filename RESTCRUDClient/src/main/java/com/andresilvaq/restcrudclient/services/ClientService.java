package com.andresilvaq.restcrudclient.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresilvaq.restcrudclient.dto.ClientDTO;
import com.andresilvaq.restcrudclient.entities.Client;
import com.andresilvaq.restcrudclient.repositories.ClientRepository;

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
	
	

}
