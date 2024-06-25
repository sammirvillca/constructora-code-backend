package com.server.constructionapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prerequisitos-construccion")
@Data
@NoArgsConstructor
public class PrerequisitoPlano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_prerequisito_plano")
    private Long id;
    private Integer roomsQuantity;
    private Integer floorsQuantity;
    private Integer bathroomsQuantity;
    private String size;
    private String tastes;
    private String dislikes;

    @Column(name = "Req_Cod", unique = true)
    private String codReq;

    @ManyToOne
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente")
    @JsonIgnore
    private Cliente cliente;

    @OneToOne(mappedBy = "prerequisitoPlano")
    @JsonIgnore
    private DibujoPlano dibujoPlano;

    @ManyToOne
    @JoinColumn(name = "cod_trabajador", referencedColumnName = "cod_trabajador")
    @JsonIgnore
    private Trabajador trabajador;

    @Transient
    public Long clienteId;

    @Transient
    public Long trabajadorId;

}
