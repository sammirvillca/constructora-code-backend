package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CatalogoProveedorDTO {
    private Long id;
    private String material;
    private Integer amountMaterial;
    private Double cost;
    private Long proveedorId;
}
