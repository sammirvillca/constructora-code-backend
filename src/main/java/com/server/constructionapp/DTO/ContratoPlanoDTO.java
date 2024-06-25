package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContratoPlanoDTO {
    private Long id;
    private String description;
    private String deadlines;
    private Long prices;
    private byte[] contractDocument;
    private Long datosClienteId;
    private Long dibujoPlanoId;
    private String codContrato;
}
