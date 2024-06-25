package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "orden-pedido-material")
@Data
@NoArgsConstructor
public class OrdenPedidoMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_orden_pedido")
    private Long id;
    private LocalDate orderDate;
    private Integer amountMaterial;

    @ManyToOne
    @JoinColumn(name = "cod_proyecto", referencedColumnName = "cod_proyecto")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "cod_catalogo", referencedColumnName = "cod_catalogo")
    private CatalogoProveedor catalogoProveedor;
}
