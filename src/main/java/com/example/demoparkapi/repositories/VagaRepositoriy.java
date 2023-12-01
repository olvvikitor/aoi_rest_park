package com.example.demoparkapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoparkapi.entities.Vaga;
import com.example.demoparkapi.enuns.StatusVaga;

@Repository
public interface VagaRepositoriy extends JpaRepository<Vaga, Long> {

	Optional<Vaga> findByCodigo(String codigo);

	Optional<Vaga> findFirstByStatus(StatusVaga livre);

}
