package com.br.delivery.entity;

// Cliente.java
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {
    @Id
    private Long id;

    private String nome;
    private String email;
    private String telefone;

}
