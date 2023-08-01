package com.br.delivery.repository;

// PedidoRepository.java
import com.br.delivery.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Adicione métodos de consulta personalizados, se necessário
}
