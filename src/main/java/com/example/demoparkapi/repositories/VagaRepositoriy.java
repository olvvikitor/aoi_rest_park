package com.example.demoparkapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoparkapi.entities.Vaga;

@Repository
public interface VagaRepositoriy extends JpaRepository<Vaga, Long> {

	Optional<Vaga> findByCodigo(String codigo);

}
