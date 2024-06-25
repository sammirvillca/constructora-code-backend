package com.server.constructionapp.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdenPedidoMaterialDTO {
    private Long id;
    private LocalDate orderDate;
    private Integer amountMaterial;
    private Long proyectoId;
    private Long catalogoProveedorId;
    private String proveedorNombre;
    private String materialNombre;
}
