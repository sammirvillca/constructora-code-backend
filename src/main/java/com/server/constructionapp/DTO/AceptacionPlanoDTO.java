package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AceptacionPlanoDTO {
    private Long id;
    private LocalDate acceptanceDate;
    private String comments;
    private Long dibujoPlanoId;
    private String estado;

}
