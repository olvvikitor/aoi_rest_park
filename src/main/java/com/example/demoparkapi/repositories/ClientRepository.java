package com.example.demoparkapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoparkapi.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	

}
