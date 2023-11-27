package com.example.demoparkapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demoparkapi.entities.Client;
import com.example.demoparkapi.repositories.projection.ClientProjection;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	
	@Query("select c from Client c")
	Page<ClientProjection> findAllpage(Pageable pageable);

	
	Client findByUserId(Long id);
	

}
