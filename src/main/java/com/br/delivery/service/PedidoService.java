package com.br.delivery.service;

// PedidoService.java
import com.br.delivery.entity.Cliente;
import com.br.delivery.entity.Pedido;
import com.br.delivery.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        return pedidoOptional.orElse(null);
    }

    public Pedido cadastrarPedido(Pedido pedido) {
        // Verifica se o cliente associado ao pedido existe
        Cliente clienteAssociado = clienteService.buscarClientePorId(pedido.getCliente().getId());
        if (clienteAssociado != null) {
            pedido.setCliente(clienteAssociado);
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    public Pedido atualizarPedido(Long id, Pedido pedido) {
        if (pedidoRepository.existsById(id)) {
            pedido.setId(id);
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    public boolean deletarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
