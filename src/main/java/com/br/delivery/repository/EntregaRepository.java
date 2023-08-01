package com.br.delivery.repository;

// EntregaRepository.java
import com.br.delivery.entity.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}

