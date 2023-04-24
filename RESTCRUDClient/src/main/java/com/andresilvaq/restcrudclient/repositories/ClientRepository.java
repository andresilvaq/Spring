package com.andresilvaq.restcrudclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andresilvaq.restcrudclient.entities.Client;

/**
 * 
 * @author andresilvaq
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
