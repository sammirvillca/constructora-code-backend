package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contrato-construccion")
@Data
@NoArgsConstructor
public class ContratoConstruccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_contrato_construccion")
    private Long id;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] constructionDoc; 

    @Column(name = "Contrato_Construccion_Cod", unique = true)
    private String codContConstruccion;

    @OneToOne
    @JoinColumn(name = "cod_proyecto", referencedColumnName = "cod_proyecto")
    @JsonIgnore
    private Proyecto proyecto;


}
