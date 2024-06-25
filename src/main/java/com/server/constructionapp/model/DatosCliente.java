package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "informacion-clientes")
@Data
@NoArgsConstructor
public class DatosCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_datos_cliente")
    private Long id;
    private String groundDirection;
    private String landArea;
    private String typeConstruction;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] propertyDoc;

    @ManyToOne
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente")
    @JsonIgnore
    private Cliente cliente;

    @OneToOne(mappedBy = "datosCliente", cascade = CascadeType.ALL)
    @JsonIgnore
    private ContratoPlano contratoPlano;

    @OneToMany(mappedBy = "datosCliente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DepositoCliente> depositoClientes;
}
