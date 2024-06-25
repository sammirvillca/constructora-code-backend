package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContratoConstruccionDTO {
    private Long id;
    private byte[] constructionDoc;
    private Long proyectoId;
    private String codContConstruccion;

}
