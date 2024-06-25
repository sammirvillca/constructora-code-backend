package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private Long id;

    @Column(name = "Cliente_Cod",unique = true)
    private String codCliente;

    private String fullName;
    private String email;
    private Long phone;
    private String address;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<PrerequisitoPlano> prerequisitoPlanos;


}
