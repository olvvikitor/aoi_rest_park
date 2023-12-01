package com.example.demoparkapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoparkapi.entities.ClientVaga;

@Repository
public interface ClientVagaRepository extends JpaRepository<ClientVaga, Long> {

}
