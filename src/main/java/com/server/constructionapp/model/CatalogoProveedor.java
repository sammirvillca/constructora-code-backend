package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "catalogo-proveedor")
@Data
@NoArgsConstructor
public class CatalogoProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_catalogo")
    private Long id;
    private String material;
    private Integer amountMaterial;
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "cod_proveedor", referencedColumnName = "cod_proveedor")
    @JsonIgnore
    private Proveedor proveedor;

    @OneToMany(mappedBy = "catalogoProveedor")
    @JsonIgnore
    private List<OrdenPedidoMaterial> pedidoMateriales;
}
