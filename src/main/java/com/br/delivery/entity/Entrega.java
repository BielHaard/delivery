package com.br.delivery.entity;

// Entrega.java
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "entrega")
public class Entrega {
    @Id
    private Long id;

    private String endereco;

    @OneToOne
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    private Pedido pedido;

}

