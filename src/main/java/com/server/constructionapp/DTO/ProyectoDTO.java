package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProyectoDTO {
    private Long id;
    private String description;
    private Long dibujoPlanoId;
    private Long cronogramaId;
    private Long encargadoId;
    private String codProyecto;
    private Long clienteId;
    private String clienteCodCliente;
    private String clienteFullName;
}
