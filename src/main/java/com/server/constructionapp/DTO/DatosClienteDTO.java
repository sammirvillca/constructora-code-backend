package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DatosClienteDTO {
    private Long id;
    private String groundDirection;
    private String landArea;
    private String typeConstruction;
    private byte[] propertyDoc;
    private Long clienteId;
}
