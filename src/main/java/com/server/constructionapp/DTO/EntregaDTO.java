package com.server.constructionapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EntregaDTO {
    private Long id;
    private LocalDate deadline;
    private Long proyectoId;
    private Long clienteId;
    private String codEntrega;
}
