package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "depositos-cliente")
@Data
@NoArgsConstructor
public class DepositoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_deposito_cliente")
    private Long id;
    private String payMethod;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] payPhoto;
    private String payDetails;
    private LocalDate payDate;

    @ManyToOne
    @JoinColumn(name = "cod_datos_cliente", referencedColumnName = "cod_datos_cliente")
    @JsonIgnore
    private DatosCliente datosCliente;
}
