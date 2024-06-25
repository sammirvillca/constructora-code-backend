package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contrato-plano")
@Data
@NoArgsConstructor
public class ContratoPlano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_contrato_plano")
    private Long id;
    private String description;
    private String deadlines;
    private Long prices;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] contractDocument;

    @Column(name = "Contrato_Cod", unique = true)
    private String codContrato;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_datos_cliente", referencedColumnName = "cod_datos_cliente")
    @JsonIgnore
    private DatosCliente datosCliente;

    @OneToOne
    @JoinColumn(name = "cod_diseño_plano", referencedColumnName = "cod_diseño_plano")
    @JsonIgnore
    private DibujoPlano dibujoPlano;

}
