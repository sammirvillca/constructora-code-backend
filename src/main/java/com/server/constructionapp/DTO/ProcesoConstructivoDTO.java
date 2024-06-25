package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProcesoConstructivoDTO {
    private Long id;
    private String revision;
    private String pointOfView;
    private String improvements;
    private Boolean meetsTheSchedule;
    private Long cronogramaId;
    private Long encargadoId;
}
