package com.br.delivery.service;

// EntregaService.java
import com.br.delivery.entity.Entrega;
import com.br.delivery.entity.Pedido;
import com.br.delivery.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final PedidoService pedidoService;

    @Autowired
    public EntregaService(EntregaRepository entregaRepository, PedidoService pedidoService) {
        this.entregaRepository = entregaRepository;
        this.pedidoService = pedidoService;
    }

    public Entrega cadastrarEntrega(Entrega entrega, Long pedidoId) {
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoId);
        if (pedido != null) {
            entrega.setPedido(pedido);
            return entregaRepository.save(entrega);
        }
        return null;
    }

    public Entrega atualizarEntrega(Long id, Entrega entrega) {
        if (entregaRepository.existsById(id)) {
            entrega.setId(id);
            return entregaRepository.save(entrega);
        }
        return null;
    }

    public boolean deletarEntrega(Long id) {
        if (entregaRepository.existsById(id)) {
            entregaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Entrega buscarEntregaPorId(Long id) {
        return entregaRepository.findById(id).orElse(null);
    }
}

