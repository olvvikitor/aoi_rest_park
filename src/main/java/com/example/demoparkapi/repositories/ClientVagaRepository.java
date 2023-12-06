package com.example.demoparkapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoparkapi.dtos.EstacionamentoResponseDto;
import com.example.demoparkapi.entities.ClientVaga;

@Repository
public interface ClientVagaRepository extends JpaRepository<ClientVaga, Long> {

	@Transactional
	EstacionamentoResponseDto findByRecibo(String recibo);

	Optional<ClientVaga> findByReciboAndDataSaidaIsNull(String recibo);

}
