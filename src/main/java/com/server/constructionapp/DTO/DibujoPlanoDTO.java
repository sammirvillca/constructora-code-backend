package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DibujoPlanoDTO {
    private Long id;
    private String atmosphere;
    private String circulationFlow;
    private String funcionality;
    private String dimensions;
    private Long prerequisitoPlanoId;
    private String codDiseño;
    private String estado;
}
