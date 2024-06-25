package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_proveedor")
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private Long phone;
    private String email;

    @Column(name = "Proveedor_Cod", unique = true)
    private String codProveedor;

    @OneToMany(mappedBy = "proveedor")
    @JsonIgnore
    private List<CatalogoProveedor> catalogo;



}
