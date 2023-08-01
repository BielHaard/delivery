package com.br.delivery.controller;

import com.br.delivery.entity.Entrega;
import com.br.delivery.service.EntregaService;
import com.br.delivery.utils.APIPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIPaths.ENTREGA)
public class EntregaController {

    private final EntregaService entregaService;

    @Autowired
    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PostMapping("/{pedidoId}")
    public ResponseEntity<Entrega> cadastrarEntrega(@RequestBody Entrega entrega, @PathVariable Long pedidoId) {
        Entrega novaEntrega = entregaService.cadastrarEntrega(entrega, pedidoId);
        if (novaEntrega != null) {
            return ResponseEntity.ok(novaEntrega);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega> atualizarEntrega(@PathVariable Long id, @RequestBody Entrega entrega) {
        Entrega entregaAtualizada = entregaService.atualizarEntrega(id, entrega);
        if (entregaAtualizada != null) {
            return ResponseEntity.ok(entregaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEntrega(@PathVariable Long id) {
        boolean entregaDeletada = entregaService.deletarEntrega(id);
        if (entregaDeletada) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscarEntregaPorId(@PathVariable Long id) {
        Entrega entrega = entregaService.buscarEntregaPorId(id);
        if (entrega != null) {
            return ResponseEntity.ok(entrega);
        }
        return ResponseEntity.notFound().build();
    }
}

